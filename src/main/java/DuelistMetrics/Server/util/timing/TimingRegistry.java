package DuelistMetrics.Server.util.timing;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class TimingRegistry {

  private static final long EPS_NANOS = 1_000L;

  private final ConcurrentHashMap<Object, TimingStats> stats = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<String, LongAdder> counters = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<Object, ConcurrentHashMap<Object, LongAdder>> edges =
      new ConcurrentHashMap<>();
  private final LongAdder errors = new LongAdder();

  public AutoCloseable start(String section) {
    final long start = System.nanoTime();
    return new AutoCloseable() {
      private volatile boolean closed = false;

      @Override
      public void close() {
        if (closed) return;
        closed = true;
        long elapsed = System.nanoTime() - start;
        if (elapsed <= 0L) return;
        stats.computeIfAbsent(section, s -> new TimingStats()).record(System.nanoTime() - start);
      }
    };
  }

  public void record(Object section, long nanos) {
    if (nanos <= 0L) return; // ignore non-positive durations
    stats.computeIfAbsent(section, s -> new TimingStats()).record(nanos);
  }

  public void increment(String name, long delta) {
    counters.computeIfAbsent(name, k -> new LongAdder()).add(delta);
  }

  public void clear() {
    stats.clear();
    counters.clear();
    edges.clear();
    errors.reset();
  }

  public Map<Object, TimingStats> checkStats() {
    return new LinkedHashMap<>(stats);
  }

  public Map<String, Long> checkCounters() {
    return counters.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().sum()));
  }

  public Map<Object, Map<Object, Long>> checkEdges() {
    Map<Object, Map<Object, Long>> out = new LinkedHashMap<>();
    for (var e : edges.entrySet()) {
      var childMap = new LinkedHashMap<Object, Long>();
      for (var ce : e.getValue().entrySet()) {
        childMap.put(ce.getKey(), ce.getValue().sum());
      }
      out.put(e.getKey(), childMap);
    }
    return out;
  }

  public int sectionCount() {
    return stats.size();
  }

  /** total parent->child links (unique child per parent key) */
  public int edgeLinkCount() {
    int n = 0;
    for (var m : edges.values()) n += m.size();
    return n;
  }

  public int counterKeyCount() {
    return counters.size();
  }

  public void recordError() {
    errors.increment();
  }

  public long errorCount() {
    return errors.sum();
  }

  private static String esc(String s) {
    if (s == null) return "";
    return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
  }

  private long totalNanosRoots() {
    // sections that appear as a child anywhere
    var childrenSet =
        edges.values().stream().flatMap(m -> m.keySet().stream()).collect(Collectors.toSet());

    long sum = 0L;
    for (var s : stats.keySet()) {
      if (!childrenSet.contains(s)) {
        var st = stats.get(s);
        if (st != null) sum += st.totalNanos();
      }
    }
    return sum;
  }

  private static String pct0(double v) {
    return String.format(Locale.US, "%.0f", v);
  }

  /**
   * Build a JSON node.
   *
   * <p>Roots (parent == null): - totalMs = section total - percentOfContext = nodeTotal /
   * contextTotal
   *
   * <p>Children (parent != null): - totalMs = EDGE time under parent - percentOfParent = edge /
   * parent.total - percentOfContext = edge / contextTotal
   *
   * <p>We also compute "unattributed" time for every node: unattributedMs = max(0, node.total -
   * sum(child edges)) unattributedPercentOfParent = (unattributedMs / node.total) * 100 This
   * represents time spent in the node that wasn't attributed to any child section.
   */
  private String buildNodeJson(
      Object node,
      long contextTotal,
      Object parent,
      Long edgeNanosFromParent // null for root; otherwise edge sum from parent->node
      ) {
    var st = stats.get(node);
    if (st == null || st.totalNanos() == 0) return null;

    final long nodeTotalNanos = st.totalNanos();

    final long displayedNanos;
    final Double percentOfParent; // null for roots
    final double percentOfContextForDisplay; // always present

    if (parent == null) {
      // Root: display the section total; % of CONTEXT
      displayedNanos = nodeTotalNanos;
      percentOfParent = null;
      percentOfContextForDisplay =
          (contextTotal > 0) ? (100.0 * nodeTotalNanos / contextTotal) : 0.0;
    } else {
      // Child: display the EDGE time; % of PARENT and % of CONTEXT
      long edge = (edgeNanosFromParent == null) ? 0L : edgeNanosFromParent;
      displayedNanos = edge;

      var pst = stats.get(parent);
      long parentTotal = (pst == null) ? 0L : pst.totalNanos();
      percentOfParent = (parentTotal > 0) ? (100.0 * edge / parentTotal) : 0.0;
      percentOfContextForDisplay = (contextTotal > 0) ? (100.0 * edge / contextTotal) : 0.0;
    }

    // Build children (ordered by edge desc); compute sum of child edges
    var kidEdges = edges.get(node);
    long sumChildEdges = 0L;
    List<String> childObjects = new ArrayList<>();

    if (kidEdges != null && !kidEdges.isEmpty()) {
      var orderedKids =
          kidEdges.entrySet().stream()
              .sorted((a, b) -> Long.compare(b.getValue().sum(), a.getValue().sum()))
              .toList();

      for (var e : orderedKids) {
        long edge = e.getValue().sum();
        sumChildEdges += edge;
        var cj = buildNodeJson(e.getKey(), contextTotal, node, edge);
        if (cj != null && !cj.isEmpty()) childObjects.add(cj);
      }
    }

    // Synthetic UNATTRIBUTED child only when this node has real children (avoid leaf spam).
    // Also suppress tiny values with EPS_NANOS.
    long unattributed = Math.max(0L, nodeTotalNanos - sumChildEdges);
    if (kidEdges != null && !kidEdges.isEmpty() && unattributed > EPS_NANOS) {
      childObjects.add(buildUnattributedChildJson(unattributed, nodeTotalNanos, contextTotal));
    }

    String childrenJson = "[" + String.join(",", childObjects) + "]";

    // Emit base fields
    StringBuilder sb = new StringBuilder(256);
    sb.append("{")
        .append("\"name\":\"")
        .append(esc(String.valueOf(node)))
        .append("\",")
        .append("\"totalMs\":")
        .append(fmt3(toMs(displayedNanos)))
        .append(",")
        .append("\"count\":")
        .append(st.count())
        .append(",")
        .append("\"avgMs\":")
        .append(fmt3(st.avgMillis()))
        .append(",")
        .append("\"minMs\":")
        .append(fmt3(toMs(st.minNanos())))
        .append(",")
        .append("\"maxMs\":")
        .append(fmt3(toMs(st.maxNanos())))
        .append(",")
        .append("\"percentOfContext\":\"")
        .append(pct0(percentOfContextForDisplay))
        .append("%\",");

    // Roots: add totalChildTimeMs (sum of edges under the root)
    if (parent == null) {
      sb.append("\"totalChildTimeMs\":").append(fmt3(toMs(sumChildEdges))).append(",");
    } else {
      // Children: include percentOfParent
      sb.append("\"percentOfParent\":\"").append(pct0(percentOfParent)).append("%\",");
    }

    // Close with children
    sb.append("\"children\":").append(childrenJson).append("}");
    return sb.toString();
  }

  private String buildUnattributedChildJson(
      long selfNanos, long parentTotalNanos, long contextTotal) {
    double pctParent = (parentTotalNanos > 0) ? (100.0 * selfNanos / parentTotalNanos) : 0.0;
    double pctContext = (contextTotal > 0) ? (100.0 * selfNanos / contextTotal) : 0.0;
    double ms = toMs(selfNanos);

    return "{"
        + "\"name\":\"UNATTRIBUTED\","
        + "\"totalMs\":"
        + fmt3(ms)
        + ","
        + "\"count\":1,"
        + "\"avgMs\":"
        + fmt3(ms)
        + ","
        + "\"minMs\":"
        + fmt3(ms)
        + ","
        + "\"maxMs\":"
        + fmt3(ms)
        + ","
        + "\"percentOfContext\":\""
        + pct0(pctContext)
        + "%\","
        + "\"percentOfParent\":\""
        + pct0(pctParent)
        + "%\","
        + "\"children\":[]"
        + "}";
  }

  private String buildSummaryJson(String label) {
    var roots = getRoots(); // roots = sections that never appear as a child
    long contextTotalRoots = Math.max(1L, totalNanosRoots()); // avoid /0

    String rootJson =
        roots.stream()
            .map(r -> buildNodeJson(r, contextTotalRoots, null, null))
            .filter(s -> s != null && !s.isEmpty())
            .collect(Collectors.joining(","));

    var c = checkCounters();
    String countersJson =
        c.isEmpty()
            ? "{}"
            : c.entrySet().stream()
                .map(e -> "\"" + esc(e.getKey()) + "\":" + e.getValue())
                .collect(Collectors.joining(",", "{", "}"));

    return "{"
        + "\"context\":\""
        + esc(label)
        + "\","
        + "\"totalMs\":"
        + fmt3(toMs(contextTotalRoots))
        + ","
        + "\"numberOfTimerErrors\":"
        + errorCount()
        + ","
        + "\"sections\":["
        + rootJson
        + "],"
        + "\"counters\":"
        + countersJson
        + "}";
  }

  public void logSummary(Logger logger, String label, Timing.OutputMode mode) {
    if (!hasData()) return;

    if (mode == Timing.OutputMode.JSON || mode == Timing.OutputMode.BOTH) {
      logger.info(buildSummaryJson(label));
      if (mode == Timing.OutputMode.JSON) return;
    }

    long total = Math.max(1L, totalNanosRoots()); // ROOTS ONLY

    var roots = getRoots();

    logger.info(label + " :: TotalMs=" + fmt3(toMs(total)) + ", timerErrors=" + errorCount());
    for (var root : roots) {
      printNode(logger, label, root, 0, total, null);
    }

    var c = checkCounters();
    if (!c.isEmpty()) {
      String joined =
          c.entrySet().stream()
              .map(e -> e.getKey() + "=" + e.getValue())
              .collect(Collectors.joining(", "));
      logger.info(label + " :: Counters :: " + joined);
    }
  }

  private List<Object> getRoots() {
    Set<Object> childrenSet =
        edges.values().stream().flatMap(m -> m.keySet().stream()).collect(Collectors.toSet());

    return stats.keySet().stream()
        .filter(s -> !childrenSet.contains(s))
        .sorted(
            (a, b) ->
                Long.compare(
                    stats.getOrDefault(b, new TimingStats()).totalNanos(),
                    stats.getOrDefault(a, new TimingStats()).totalNanos()))
        .toList();
  }

  public void logSummary(Logger logger, String label) {
    logSummary(logger, label, Timing.getOutputMode());
  }

  public boolean hasData() {
    if (!stats.isEmpty()) return true;
    // counters could exist but all be 0; treat as empty unless any > 0
    for (var adder : counters.values()) {
      if (adder.sum() > 0L) return true;
    }
    return false;
  }

  public void recordEdge(Object parent, Object child, long nanos) {
    if (child == null || parent == null) return; // root child has no parent
    if (parent == child) return;
    if (nanos <= 0L) return;
    edges
        .computeIfAbsent(parent, k -> new ConcurrentHashMap<>())
        .computeIfAbsent(child, k -> new LongAdder())
        .add(nanos);
  }

  private void printNode(
      Logger logger,
      String label,
      Object node,
      int depth,
      long contextTotal,
      Object parent) {

    var st = stats.get(node);
    if (st == null || st.totalNanos() == 0) return;

    final long nodeTotal = st.totalNanos();
    final double pctOfContext = (100.0 * nodeTotal) / Math.max(1L, contextTotal);

    // Edge map for this node’s children + sum of edges (child-attributed time)
    var kidEdges = edges.get(node);
    long sumChildEdges = 0L;
    List<Map.Entry<Object, LongAdder>> orderedKids = List.of();
    if (kidEdges != null && !kidEdges.isEmpty()) {
      orderedKids =
          kidEdges.entrySet().stream()
              .sorted((a, b) -> Long.compare(b.getValue().sum(), a.getValue().sum()))
              .toList();
      for (var e : orderedKids) sumChildEdges += e.getValue().sum();
    }

    // Build baseline for this node
    String indent = "  ".repeat(Math.max(0, depth));
    StringBuilder line =
        new StringBuilder(256)
            .append(indent)
            .append(label)
            .append(" :: ")
            .append(node)
            .append(" totalMs=")
            .append(fmt3(toMs(nodeTotal)))
            .append(" (pctAll=")
            .append(fmt1(pctOfContext))
            .append("%)");

    if (parent != null) {
      // pctParent for *this node* as a child of its parent = edge(parent->node)/parent.total
      var p = stats.get(parent);
      long parentTotal = (p == null) ? 0L : p.totalNanos();
      var parentEdges = edges.getOrDefault(parent, new ConcurrentHashMap<>());
      long edgeNanos = parentEdges.getOrDefault(node, new LongAdder()).sum();
      double pctOfParent = parentTotal > 0 ? (100.0 * edgeNanos) / parentTotal : 0.0;
      line.append(", pctParent=").append(fmt1(pctOfParent)).append("%");
    } else {
      // Root: also show total child-attributed time
      line.append(", childTimeMs=").append(fmt3(toMs(sumChildEdges)));
    }

    line.append(", count=")
        .append(st.count())
        .append(", avgMs=")
        .append(fmt3(st.avgMillis()))
        .append(", minMs=")
        .append(fmt3(toMs(st.minNanos())))
        .append(", maxMs=")
        .append(fmt3(toMs(st.maxNanos())));

    logger.info(line.toString());

    // Recurse into child sections (ordered by edge time desc)
    if (!orderedKids.isEmpty()) {
      for (var child : orderedKids) {
        printNode(logger, label, child.getKey(), depth + 1, contextTotal, node);
      }
      // Only emit UNATTRIBUTED line if there were children
      long selfNanos = Math.max(0L, nodeTotal - sumChildEdges);
      if (selfNanos > EPS_NANOS) {
        double pctAll = (contextTotal > 0) ? (100.0 * selfNanos / contextTotal) : 0.0;
        double pctPar = (nodeTotal > 0) ? (100.0 * selfNanos / nodeTotal) : 0.0;
        String indentChild = "  ".repeat(depth + 1);
        logger.info(
            indentChild
                + label
                + " :: UNATTRIBUTED totalMs="
                + fmt3(toMs(selfNanos))
                + " (pctAll="
                + fmt1(pctAll)
                + "%, pctParent="
                + fmt1(pctPar)
                + "%)"
                + ", count=1, avgMs="
                + fmt3(toMs(selfNanos))
                + ", minMs="
                + fmt3(toMs(selfNanos))
                + ", maxMs="
                + fmt3(toMs(selfNanos)));
      }
    }
  }

  private static double toMs(long nanos) {
    return nanos / 1_000_000.0;
  }

  private static String fmt1(double v) {
    return String.format(Locale.US, "%.1f", v);
  }

  private static String fmt3(double v) {
    return String.format(Locale.US, "%.3f", v);
  }

  public static final class TimingStats {
    private final LongAdder count = new LongAdder();
    private final LongAdder totalNanos = new LongAdder();
    private final AtomicLong minNanos = new AtomicLong(Long.MAX_VALUE);
    private final AtomicLong maxNanos = new AtomicLong(0L);

    void record(long nanos) {
      count.increment();
      totalNanos.add(nanos);
      minNanos.updateAndGet(prev -> Math.min(prev, nanos));
      maxNanos.updateAndGet(prev -> Math.max(prev, nanos));
    }

    public long count() {
      return count.sum();
    }

    public long totalNanos() {
      return totalNanos.sum();
    }

    public long minNanos() {
      long v = minNanos.get();
      return v == Long.MAX_VALUE ? 0 : v;
    }

    public long maxNanos() {
      return maxNanos.get();
    }

    public double avgMillis() {
      long c = count();
      return c == 0 ? 0 : (totalNanos() / 1_000_000.0) / c;
    }
  }
}

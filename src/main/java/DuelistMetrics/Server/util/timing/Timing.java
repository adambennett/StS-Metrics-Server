package DuelistMetrics.Server.util.timing;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

public final class Timing {

  public enum OutputMode {
    TEXT,
    JSON,
    BOTH
  }

  private static final Logger logger = Logger.getLogger("DuelistMetrics.Server.Timing");
  private static volatile boolean ENABLED = true;
  private static final AutoCloseable NOOP = () -> {};
  private static volatile OutputMode OUTPUT_MODE = OutputMode.JSON;
  private static volatile boolean HEAP_SAMPLING_ON_CLEAR = true;

  // One registry per "context"
  private static final ConcurrentHashMap<String, TimingRegistry> HUB = new ConcurrentHashMap<>();
  private static final ThreadLocal<String> CTX = ThreadLocal.withInitial(() -> "default");
  private static final ThreadLocal<Deque<Frame>> FRAMES = ThreadLocal.withInitial(ArrayDeque::new);
  private static final ThreadLocal<Deque<Token>> TOKEN_STACK =
      ThreadLocal.withInitial(ArrayDeque::new);

  // Do not create instance - use static
  private Timing() {}

  public static void setEnabled(boolean enabled) {
    ENABLED = enabled;
  }

  public static void setHeapSamplingOnClear(boolean enabled) {
    HEAP_SAMPLING_ON_CLEAR = enabled;
  }

  public static String currentContextName() {
    return CTX.get();
  }

  /** Create or switch to a named timing context for the current thread. */
  public static Scope open(String name) {
    Objects.requireNonNull(name, "context name");
    var prev = CTX.get();
    CTX.set(name);
    HUB.computeIfAbsent(name, k -> new TimingRegistry());
    return new Scope(name, prev);
  }

  public static AutoPrintingScope openWithAutoPrint(String name, Logger log) {
    Objects.requireNonNull(name, "context name");
    var prev = CTX.get();
    CTX.set(name);
    HUB.computeIfAbsent(name, k -> new TimingRegistry());
    var useLog = (log == null) ? logger : log;
    return new AutoPrintingScope(name, prev, useLog);
  }

  /** Re-enter a context for this thread, returning an AutoCloseable to restore afterward. */
  public static AutoCloseable reenter(String name) {
    var prev = CTX.get();
    CTX.set(name);
    HUB.computeIfAbsent(name, k -> new TimingRegistry());
    return () -> CTX.set(prev);
  }

  /** Utility to run code inside a context (useful for parallel tasks). */
  public static void runInScope(String name, Runnable r) {
    try (var ignored = reenter(name)) {
      r.run();
    } catch (Throwable ex) {
      logger.info("Error while calling runInScope()"+ ExceptionUtils.getStackTrace(ex));
      if (ex instanceof RuntimeException rt) throw rt;
      throw new RuntimeException(ex);
    }
  }

  /** Utility to call code inside a context (useful for parallel tasks). */
  public static <T> T callInScope(String name, Callable<T> c) {
    try (var ignored = reenter(name)) {
      return c.call();
    } catch (Exception e) {
      logger.info("Error while calling callInScope()"+ ExceptionUtils.getStackTrace(e));
      if (e instanceof RuntimeException rt) throw rt;
      throw new RuntimeException(e);
    }
  }

  /** Start a timer for the current context (use inside try-with-resources). */
  public static AutoCloseable start(String section) {
    return startInternal(section);
  }

  // Is there an active section on this thread?
  public static boolean hasActiveSection() {
    return currentParent() != null;
  }

  /**
   * Start 'section' only if there is no active section. Returns NOOP when a parent already exists.
   */
  public static AutoCloseable maybeStart(String section) {
    return hasActiveSection() ? NOOP : start(section);
  }

  public static AutoCloseable forceParent(String section) {
      if (!ENABLED) return NOOP;
      return Objects.equals(currentParent(), section) ? NOOP : startInternal(section);
  }

  /** Run body inside a default parent if none exists (Runnable). */
  public static void withDefaultParent(String section, Runnable body) {
    try (var ignored = maybeStart(section)) {
      body.run();
    } catch (Exception ex) {
      logger.info("Error calling withDefaultParent-Runnable"+ ExceptionUtils.getStackTrace(ex));
    }
  }

  /** Run body inside a default parent if none exists (Supplier<T>). */
  public static <T> T withDefaultParent(String section, Supplier<T> body) {
    try (var ignored = maybeStart(section)) {
      return body.get();
    } catch (Exception ex) {
      logger.info("Error calling withDefaultParent-Supplier"+ ExceptionUtils.getStackTrace(ex));
      throw (ex instanceof RuntimeException re) ? re : new RuntimeException(ex);
    }
  }

    /** Run body inside a forced parent section (Runnable). */
    public static void withForcedParent(String section, Runnable body) {
        try (var ignored = forceParent(section)) {
            body.run();
        } catch (Exception ex) {
            logger.info("Error calling withForcedParent-Runnable"+ ExceptionUtils.getStackTrace(ex));
        }
    }

    /** Run body inside a forced parent section (Supplier<T>). */
    public static <T> T withForcedParent(String section, Supplier<T> body) {
        try (var ignored = forceParent(section)) {
            return body.get();
        } catch (Exception ex) {
            logger.info("Error calling withForcedParent-Supplier"+ ExceptionUtils.getStackTrace(ex));
            throw (ex instanceof RuntimeException re) ? re : new RuntimeException(ex);
        }
    }

  /** Increment a counter in the CURRENT context. */
  public static void increment(String name, long delta) {
    if (ENABLED) currentRegistry().increment(name, delta);
  }

  /** Log a single context summary. */
  public static void logSummary(String context, Logger logger) {
    var r = HUB.get(context);
    if (r != null && r.hasData()) r.logSummary(logger, context, OUTPUT_MODE);
  }

  /** Clear a specific context (free memory). */
  public static void clear(String context) {
    var reg = HUB.remove(context);
    if (reg == null) return;

    // sample object counts
    int sections = reg.sectionCount();
    int edges = reg.edgeLinkCount();
    int counters = reg.counterKeyCount();

    boolean sampled = false;
    long before = 0L, after = 0L, delta = 0L;

    if (HEAP_SAMPLING_ON_CLEAR) {
      sampled = true;
      before = currentHeapUsed();
    }

    reg.clear(); // help GC

    if (sampled) {
      after = currentHeapUsed();
      delta = after - before; // can be negative or zero
    }

    // build JSON
    StringBuilder sb = new StringBuilder(256);
    sb.append("{\"event\":\"timing.clear\",\"context\":\"")
        .append(esc(context))
        .append("\",")
        .append("\"sections\":")
        .append(sections)
        .append(",")
        .append("\"edgeLinks\":")
        .append(edges)
        .append(",")
        .append("\"counterKeys\":")
        .append(counters);

    appendSampledBytes(sampled, before, after, delta, sb);
  }

  private static void appendSampledBytes(
      boolean sampled, long before, long after, long delta, StringBuilder sb) {
    if (sampled) {
      sb.append(",\"heapUsedBeforeBytes\":")
          .append(before)
          .append(",\"heapUsedBefore\":\"")
          .append(esc(humanBytes(before)))
          .append("\"")
          .append(",\"heapUsedAfterBytes\":")
          .append(after)
          .append(",\"heapUsedAfter\":\"")
          .append(esc(humanBytes(after)))
          .append("\"")
          .append(",\"deltaBytes\":")
          .append(delta)
          .append(",\"delta\":\"")
          .append(esc(humanBytes(delta)))
          .append("\"")
          .append("}");
      logger.info(sb.toString());
    }
  }

  /** Clear everything (rare; e.g., post-test). */
  public static void clearAll() {
    int contexts = HUB.size();
    int sections = 0, edges = 0, counters = 0;

    for (TimingRegistry r : HUB.values()) {
      sections += r.sectionCount();
      edges += r.edgeLinkCount();
      counters += r.counterKeyCount();
    }

    boolean sampled = false;
    long before = 0L, after = 0L, delta = 0L;

    if (HEAP_SAMPLING_ON_CLEAR) {
      sampled = true;
      before = currentHeapUsed();
    }

    HUB.clear();

    if (sampled) {
      after = currentHeapUsed();
      delta = after - before;
    }

    StringBuilder sb = new StringBuilder(256);
    sb.append("{\"event\":\"timing.clearAll\",")
        .append("\"contexts\":")
        .append(contexts)
        .append(",")
        .append("\"sections\":")
        .append(sections)
        .append(",")
        .append("\"edgeLinks\":")
        .append(edges)
        .append(",")
        .append("\"counterKeys\":")
        .append(counters);

    appendSampledBytes(sampled, before, after, delta, sb);
  }

  private static String humanBytes(long bytes) {
    final double KB = 1024.0;
    final double MB = KB * 1024.0;
    final double GB = MB * 1024.0;
    Locale L = Locale.US;
    if (bytes >= GB) return String.format(L, "%.2f GB", bytes / GB);
    if (bytes >= MB) return String.format(L, "%.2f MB", bytes / MB);
    if (bytes >= KB) return String.format(L, "%.2f KB", bytes / KB);
    return bytes + " B";
  }

  public static void logAll(Logger logger) {
    for (Map.Entry<String, TimingRegistry> e : HUB.entrySet()) {
      if (e.getValue().hasData()) {
        e.getValue().logSummary(logger, e.getKey());
      }
    }
  }

  private static long currentHeapUsed() {
    var rt = Runtime.getRuntime();
    return rt.totalMemory() - rt.freeMemory();
  }

  private static String esc(String s) {
    if (s == null) return "";
    return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
  }

  private static TimingRegistry currentRegistry() {
    return HUB.computeIfAbsent(CTX.get(), k -> new TimingRegistry());
  }

  private static void recordTimerError() {
    try {
      currentRegistry().recordError();
    } catch (Throwable ignored) {
    }
  }

  /**
   * Binds this thread to a named timing context while the Scope is open.
   *
   * <p>When to use:
   *
   * <p>- Before kicking off a unit of work whose timings you want grouped together (e.g., per
   * report template)
   *
   * <p>- When running parallel tasks, and you want each task to record into a specific context
   *
   * <p>Example: // per-template context name String ctx = template.getTemplateName() + "-" +
   * template.getTenantId(); try (var scope = Timing.open(ctx)) { // Anything timed here lands in
   * 'ctx' try (var ignored = Timing.start(TimingSection.SETUP)) { ... } // Pass context into
   * parallel work: list.parallelStream() .map(scope.wrap(item -> compute(item))) // Supplier<T>
   * wrapped in context .forEach(scope.wrap(res -> consume(res))); // Runnable wrapped in context }
   *
   * <p>You can also re-enter later threads with: Timing.runInScope(ctx, () -> doWork()); var value
   * = Timing.callInScope(ctx, () -> compute());
   */
  public record Scope(String name, String prev) implements AutoCloseable {

    /** Wrap a Runnable so it runs inside this scope (great for thread pools / parallelStream). */
    public Runnable wrap(Runnable r) {
      return () -> Timing.runInScope(name, r);
    }

    /** Wrap a Supplier so it runs inside this scope and returns a value. */
    public <T> Supplier<T> wrap(Supplier<T> s) {
      return () -> Timing.callInScope(name, s::get);
    }

    /** Wrap a Function so it runs inside this scope (handy for stream map). */
    public <T, R> Function<T, R> wrap(Function<T, R> f) {
      return t -> Timing.callInScope(name, () -> f.apply(t));
    }

    public <T> Consumer<T> wrap(Consumer<T> c) {
      return t -> Timing.runInScope(name, () -> c.accept(t));
    }

    @Override
    public void close() {
      CTX.set(prev);
    }
  }

  public record AutoPrintingScope(String name, String prev, Logger log)
      implements AutoCloseable {

    public Runnable wrap(Runnable r) {
      return () -> Timing.runInScope(name, r);
    }

    public <T> Supplier<T> wrap(Supplier<T> s) {
      return () -> Timing.callInScope(name, s::get);
    }

    public <T, R> Function<T, R> wrap(Function<T, R> f) {
      return t -> Timing.callInScope(name, () -> f.apply(t));
    }

    public <T> Consumer<T> wrap(Consumer<T> c) {
      return t -> Timing.runInScope(name, () -> c.accept(t));
    }

    @Override
    public void close() {
      try {
        logSummary(name, log);
      } catch (Throwable t) {
        logger.info("AutoPrintingScope: error logging summary for " + name + ": " + ExceptionUtils.getStackTrace(t));
      } finally {
        try {
          Timing.clear(name);
        } catch (Throwable t) {
          logger.info("AutoPrintingScope: error clearing " + name + ": " + ExceptionUtils.getStackTrace(t));
        } finally {
          CTX.set(prev);
        }
      }
    }
  }

  private record Frame(Object section, long startNanos, Object parent) {}

  private static Object currentParent() {
    var tq = TOKEN_STACK.get();
    Token tok = (tq != null) ? tq.peekFirst() : null;

    var dq = FRAMES.get();
    Frame fr = (dq != null) ? dq.peekFirst() : null;

    if (tok == null) return (fr != null) ? fr.section : null;
    if (fr == null) return tok.section;
    // last-opened wins
    return (tok.start > fr.startNanos) ? tok.section : fr.section;
  }

  /** Begin timing for the current context using paired calls; supports nesting. */
  public static void enter(String section) {
    enterInternal(section);
  }

  /** End timing for the current context using paired calls. */
  public static void exit(String section) {
    exitInternal(section);
  }

  /**
   * Begin/end timing a section without creating a try-with-resources block.
   *
   * <p>When to use:
   *
   * <p>- You want to time across lines that assign to locals (avoid extra braces)
   *
   * <p>- Spans that cross small helper boundaries where try-with-resources feels clunky
   *
   * <p>Example: var tok = Timing.begin(TimingSection.GBA_RULE_EVAL); foo = computeFoo(); bar =
   * computeBar(foo); Timing.end(tok);
   *
   * <p>Tokens capture the current parent at 'begin', so they appear as children in the hierarchy
   * under whatever was active at that time.
   */
  public record Token(Object section, Object parent, long start) {}

  public static Token begin(String section) {
    return beginInternal(section);
  }

  public static void end(Token t) {
    if (!ENABLED || t == null || t.start < 0) return;
    long nanos = System.nanoTime() - t.start;
    currentRegistry().record(t.section, nanos);
    currentRegistry().recordEdge(t.parent, t.section, nanos);

    var tq = TOKEN_STACK.get();
    var top = (tq != null) ? tq.pollFirst() : null;
    if (top != t) {
      logger.info("Timing.end token mismatch: expected same token, got different/none");
      recordTimerError();
    }
  }

  /**
   * Run a lambda and record its duration.
   *
   * <p>When to use:
   *
   * <p>- Short, self-contained blocks where you don't need intermediate locals
   *
   * <p>- Quick instrumentation with minimal syntax
   *
   * <p>Example:
   *
   * <p>Timing.measure(TimingSection.SOME_SECTION, () -> {
   *
   * <p>normalizeInputs();
   *
   * <p>hydrateIndexes();
   *
   * <p>});
   *
   * <p>
   *
   * <p>NOTE: By default 'measure' records the section total but does NOT push/pop the per-thread
   * frame stack, so it will not appear as a child in the hierarchy. If you want hierarchical
   * attribution, use 'measureHierarchical(...)'.
   */
  public static void measure(String section, Runnable r) {
    if (!ENABLED) {
      r.run();
      return;
    }
    long start = System.nanoTime();
    try {
      r.run();
    } finally {
      currentRegistry().record(section, System.nanoTime() - start);
    }
  }

  public static <T> T measure(String section, Supplier<T> s) {
    if (!ENABLED) return s.get();
    long start = System.nanoTime();
    try {
      return s.get();
    } finally {
      currentRegistry().record(section, System.nanoTime() - start);
    }
  }

  /** Like measure(), but participates in the hierarchy (appears as a child). */
  public static void measureHierarchical(String section, Runnable r) {
    if (!ENABLED) {
      r.run();
      return;
    }
    final Object parent = currentParent();
    final long start = System.nanoTime();
    FRAMES.get().push(new Frame(section, start, parent));
    try {
      r.run();
    } finally {
      FRAMES.get().pollFirst();
      long nanos = System.nanoTime() - start;
      currentRegistry().record(section, nanos);
      currentRegistry().recordEdge(parent, section, nanos);
    }
  }

  public static <T> T measureHierarchical(String section, Supplier<T> s) {
    if (!ENABLED) return s.get();
    final Object parent = currentParent();
    final long start = System.nanoTime();
    FRAMES.get().push(new Frame(section, start, parent));
    try {
      return s.get();
    } finally {
      FRAMES.get().pollFirst();
      long nanos = System.nanoTime() - start;
      currentRegistry().record(section, nanos);
      currentRegistry().recordEdge(parent, section, nanos);
    }
  }

  public static void setOutputMode(OutputMode mode) {
    OUTPUT_MODE = (mode == null) ? OutputMode.TEXT : mode;
  }

  static OutputMode getOutputMode() {
    return OUTPUT_MODE;
  }

  static final class DynamicSection {
    private static final ConcurrentHashMap<String, Named> INTERN = new ConcurrentHashMap<>();

    static Object of(String name) {
      if (name == null) return null;
      return INTERN.computeIfAbsent(name, Named::new);
    }

    @SuppressWarnings("NullableProblems")
    private record Named(String name) {
      @Override
      public String toString() {
        return name;
      }

      @Override
      public boolean equals(Object o) {
        return (o instanceof Named n) && name.equals(n.name);
      }

      @Override
      public int hashCode() {
        return name.hashCode();
      }
    }
  }

  private static AutoCloseable startInternal(Object section) {
    if (!ENABLED) return NOOP;
    final Object parent = currentParent();
    final long start = System.nanoTime();
    FRAMES.get().push(new Frame(section, start, parent));
    return () -> {
      var f = FRAMES.get().pollFirst();
      if (f == null || f.section != section) {
        logger.info(
            "Timing.start/close out of order: expected="
                + (f != null ? f.section : null)
                + ", got="
                + section);
        recordTimerError();
        return;
      }
      long nanos = System.nanoTime() - start;
      currentRegistry().record(section, nanos);
      currentRegistry().recordEdge(f.parent, section, nanos);
    };
  }

  private static void enterInternal(Object section) {
    if (!ENABLED) return;
    FRAMES.get().push(new Frame(section, System.nanoTime(), currentParent()));
  }

  private static void exitInternal(Object section) {
    if (!ENABLED) return;
    var f = FRAMES.get().pollFirst();
    if (f == null || f.section != section) {
      logger.info(
          "Timing.exit out of order: expected="
              + (f != null ? f.section : null)
              + ", got="
              + section);
      recordTimerError();
      return;
    }
    long nanos = System.nanoTime() - f.startNanos;
    currentRegistry().record(section, nanos);
    currentRegistry().recordEdge(f.parent, section, nanos);
  }

  private static Token beginInternal(Object section) {
    if (!ENABLED) return new Token(section, null, -1L);
    final Object parent = currentParent();
    final long start = System.nanoTime();
    Token t = new Token(section, parent, start);
    TOKEN_STACK.get().push(t);
    return t;
  }

  public static AutoCloseable autoSwitch(Object key) {
    if (!ENABLED || key == null) return NOOP;
    final Token token = beginInternal(DynamicSection.of(String.valueOf(key)));
    return new AutoCloseable() {
      private boolean closed = false;

      @Override
      public void close() {
        if (closed) return;
        closed = true;
        try {
          end(token);
        } catch (Throwable ignored) {
          /* don't mask caller exceptions */
        }
      }

      @Override
      public String toString() {
        return "Timing.autoSwitch(" + key + ")";
      }
    };
  }

  public static Token autoSwitchToken(Object key) {
    if (!ENABLED || key == null) return new Token(null, null, -1L);
    return beginInternal(DynamicSection.of(String.valueOf(key)));
  }

  public static Deque<Object> captureParentStack() {
    var dq = FRAMES.get();
    Deque<Object> copy = new ArrayDeque<>();
    if (dq != null && !dq.isEmpty()) {
      // FRAMES is top-first; we want bottom->top
      for (var it = dq.descendingIterator(); it.hasNext(); ) {
        Frame f = it.next();
        copy.addLast(f.section());   // <-- section, not Frame
      }
    }
    return copy;
  }

  /** Convenience: run in context and with a ghost parent stack. */
  public static <T> T callInScopeWithParents(String ctx, Deque<Object> parents, Callable<T> c) {
    return callInScope(ctx, () -> {
      try (var ignored = pushGhostParents(parents)) {
        return c.call();
      }
    });
  }

  /** Push a "ghost" parent stack: provides parentage but records no time for the frames themselves. */
  private static AutoCloseable pushGhostParents(Deque<Object> parents) {
    if (parents == null || parents.isEmpty()) return NOOP;
    for (Object p : parents) FRAMES.get().push(new Frame(p, System.nanoTime(), currentParent()));
    return () -> {
      // Pop exactly what we pushed, without recording
      for (int i = 0; i < parents.size(); i++) {
        FRAMES.get().pollFirst(); // do NOT call exitInternal()
      }
    };
  }
}

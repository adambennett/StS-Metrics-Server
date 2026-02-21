package DuelistMetrics.Server.util.timing;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public final class TimingStats {
  private final LongAdder count = new LongAdder();
  private final LongAdder totalNanos = new LongAdder();
  private final AtomicLong minNanos = new AtomicLong(Long.MAX_VALUE);
  private final AtomicLong maxNanos = new AtomicLong(0L);

  public void record(long nanos) {
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

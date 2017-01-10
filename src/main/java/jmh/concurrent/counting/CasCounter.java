package jmh.concurrent.counting;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class CasCounter {
    private final Supplier<LocalTime> time;
    private LocalTime curTime;
    private long prevValue = 0;
    private AtomicLong curCounter = new AtomicLong();

    public CasCounter() {
        this.time = () -> LocalTime.now().withSecond(0).withNano(0);
        curTime = time.get();
    }

    CasCounter(Supplier<LocalTime> time) {
        this.time = time;
        curTime = time.get();
    }

    public long increment() {
        swapTime();
        return curCounter.incrementAndGet();
    }

    public long get() {
        swapTime();
        return prevValue;
    }

    public long getCurrent() {
        return curCounter.get();
    }

    private void swapTime() {
        long curValue = curCounter.get();
        LocalTime time = this.time.get();
        if (!time.equals(curTime)) {
            boolean updated = curCounter.compareAndSet(curValue, 0);
            if (updated) {
                curTime = time;
                prevValue = curValue;
            }
        }
    }
}


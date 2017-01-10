package jmh.concurrent.counting;

import java.time.LocalTime;
import java.util.function.Supplier;

public class LockerSyncCounter {
    private final Supplier<LocalTime> time;
    private LocalTime curTime;
    private long prevValue = 0;
    private long curValue = 0;
    private final Object lock = new Object();

    public LockerSyncCounter() {
        this.time = () -> LocalTime.now().withSecond(0).withNano(0);
        curTime = time.get();
    }

    LockerSyncCounter(Supplier<LocalTime> time) {

        this.time = time;
        curTime = time.get();
    }

    public long increment() {
        synchronized (lock) {
            LocalTime time = this.time.get();
            if (!time.equals(curTime)) {
                curTime = time;
                prevValue = curValue;
                curValue = 0;
            }
            curValue++;
        }
        return prevValue;
    }

    public long get() {
        return prevValue;
    }

    public long getCurrent() {
        return curValue;
    }
}

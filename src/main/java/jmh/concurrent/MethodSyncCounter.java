package jmh.concurrent;

import java.time.LocalTime;
import java.util.function.Supplier;

public class MethodSyncCounter {
    private final Supplier<LocalTime> time;
    private LocalTime curTime;
    private long prevValue = 0;
    private long curValue = 0;

    public MethodSyncCounter() {
        this.time = () -> LocalTime.now().withSecond(0).withNano(0);
        curTime = time.get();
    }

    public synchronized long increment() {
        LocalTime time = this.time.get();
        if (!time.equals(curTime)) {
            curTime = time;
            prevValue = curValue;
            curValue = 0;
        }
        curValue++;
        return prevValue;
    }
}

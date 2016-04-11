package benchmark.concurrent;

import jmh.concurrent.CasCounter;
import jmh.concurrent.LockerSyncCounter;
import jmh.concurrent.MethodSyncCounter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Group)
public class CounterBenchmark {
    public final CasCounter casCounter = new CasCounter();
    public final MethodSyncCounter methodSyncCounter = new MethodSyncCounter();
    public final LockerSyncCounter lockerSyncCounter = new LockerSyncCounter();

    @Benchmark
    @Group("cas")
    public long cas1() {
        return casCounter.increment();
    }

    @Benchmark
    @Group("cas")
    public long cas2() {
        return casCounter.increment();
    }

    @Benchmark
    @Group("cas")
    public long cas3() {
        return casCounter.increment();
    }

    @Benchmark
    @Group("cas")
    public long cas4() {
        return casCounter.increment();
    }

    @Benchmark
    @Group("lock")
    public long lock1() {
        return lockerSyncCounter.increment();
    }

    @Benchmark
    @Group("lock")
    public long lock2() {
        return lockerSyncCounter.increment();
    }

    @Benchmark
    @Group("lock")
    public long lock3() {
        return lockerSyncCounter.increment();
    }

    @Benchmark
    @Group("lock")
    public long lock4() {
        return lockerSyncCounter.increment();
    }

    @Benchmark
    @Group("methodSync")
    public long methodSync1() {
        return methodSyncCounter.increment();
    }

    @Benchmark
    @Group("methodSync")
    public long methodSync2() {
        return methodSyncCounter.increment();
    }

    @Benchmark
    @Group("methodSync")
    public long methodSync3() {
        return methodSyncCounter.increment();
    }

    @Benchmark
    @Group("methodSync")
    public long methodSync4() {
        return methodSyncCounter.increment();
    }

}

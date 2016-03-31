package benchmark;

import jmh.JCreateList;
import jmh.KCreateList;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.function.IntFunction;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class CreateArrayListBenchmark {

    int data[];

    @Setup
    public void setup() {
        data = Utils.createData(100000000, i -> i % 100);
    }

    @Benchmark
    public List<Integer> javaByStream() {
        return JCreateList.createByStream(data);
    }

    @Benchmark
    public List<Integer> javaByForCycle() {
        return JCreateList.createByForCycle(data);
    }

    @Benchmark
    public List<Integer> javaByForEnchanced() {
        return JCreateList.createByForEnhanced(data);
    }

    @Benchmark
    public List<Integer> javaByForCycleAsLinkedList() {
        return JCreateList.createByForCycleAsLinkedList(data);
    }

    @Benchmark
    public List<Integer> kotlinToList() {
        return KCreateList.Companion.createToList(data);
    }
}

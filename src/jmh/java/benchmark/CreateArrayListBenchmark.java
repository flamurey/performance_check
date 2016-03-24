package benchmark;

import jmh.JCreateList;
import jmh.KCreateList;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.List;
import java.util.function.IntFunction;

@State(Scope.Benchmark)
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

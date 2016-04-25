package benchmark;

import jmh.JCreateList;
import jmh.JNumberFinder;
import jmh.KNumberFinder;
import org.openjdk.jmh.annotations.*;

import java.util.List;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class FindNumberBenchmark {

    int number;
    int data[];
    Integer dataWrapped[];
    List<Integer> listData;

    @Setup
    public void setup() {
        number = 20;
        data = Utils.createData(100000000, i -> i % 100);
        dataWrapped = Utils.convertIntArrayToWrapped(data);
        listData = JCreateList.createByForCycle(data);
    }

    @Benchmark
    public int kotlinByStream() {
        return KNumberFinder.Companion.findByStream(data, number);
    }

    @Benchmark
    public int kotlinByLazySequence() {
        return KNumberFinder.Companion.findByLazySequence(data, number);
    }
}

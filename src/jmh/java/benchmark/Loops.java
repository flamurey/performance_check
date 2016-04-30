package benchmark;

import jmh.Kotlin;
import jmh.Scala;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class Loops {
    int n = 100;

    @Benchmark
    public long scalaForLoop() {
        return Scala.forLoop(n);
    }

    @Benchmark
    public long scalaWhileLoop() {
        return Scala.whileLoop(n);
    }

    @Benchmark
    public long kotlinRangeLoop() {
        return Kotlin.rangeLoop(n);
    }

    @Benchmark
    public long kotlinRepeatLoop() {
        return Kotlin.repeatLoop(n);
    }
}
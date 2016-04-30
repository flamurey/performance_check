package benchmark;

import jmh.Scala;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class Loops {
    int n = 100;

    @Benchmark
    public long forLoop() {
        return Scala.forLoop(n);
    }

    @Benchmark
    public long whileLoop() {
        return Scala.whileLoop(n);
    }
}
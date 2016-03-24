package benchmark;

import jmh.JFibonacci;
import jmh.KFibonacci;
import jmh.Scala;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class FibonacciBenchmark {
    int n = 100000;

    @Benchmark
    public long scala() {
        return Scala.fibRec(n, 0, 1);
    }

    @Benchmark
    public long javaRec() {
        return JFibonacci.fibRec(n, 0, 1);
    }

    @Benchmark
    public long javaFastLoop() {
        return JFibonacci.fastLoop(n, 0, 1);
    }

    @Benchmark
    public long kotlinRec() {
        return KFibonacci.Companion.fibRec(n, 0, 1);
    }
}
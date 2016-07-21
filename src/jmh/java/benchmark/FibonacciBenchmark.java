package benchmark;

import jmh.FibonacciLogN;
import jmh.JFibonacci;
import jmh.KFibonacci;
import jmh.Scala;
import org.openjdk.jmh.annotations.*;

import java.math.BigInteger;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.SampleTime)
public class FibonacciBenchmark {
    int n = 100000000;

//    @Benchmark
//    public long scala() {
//        return Scala.fibRec(n, 0, 1);
//    }
//
//    @Benchmark
//    public long javaRec() {
//        return JFibonacci.fibRec(n, 0, 1);
//    }
//
//    @Benchmark
//    public BigInteger javaFastLoop() {
//        return JFibonacci.fastLoop(n, BigInteger.valueOf(0), BigInteger.valueOf(1));
//    }

    @Benchmark
    public BigInteger javaLogN() {
        return FibonacciLogN.get(n);
    }

//    @Benchmark
//    public long kotlinRec() {
//        return KFibonacci.Companion.fibRec(n, 0, 1);
//    }
}

package benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class AwfulBenchmark {

    int i1 = 5;
    int i2 = 11;

    long ll1 = 5;
    long ll2 = 11;


    @Benchmark
    public long longSum() {
        long l1 = ll1;
        long l2 = ll2;
        return l1 + l2;
    }

    @Benchmark
    public long wrongLongSum() {
        long l1 = i1;
        long l2 = i2;
        return l1 + l2;
    }
}

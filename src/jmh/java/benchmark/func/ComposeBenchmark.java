package benchmark.func;

import kotlin.jvm.functions.Function1;
import kscala.func.func;
import org.openjdk.jmh.annotations.*;
import scala.func.package$;
import scala.runtime.AbstractFunction1;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class ComposeBenchmark {
    int x = -3;

    scala.Function1 sDouble = new AbstractFunction1<Integer, Integer>() {
        @Override
        public Integer apply(Integer v1) {
            return v1 * 2;
        }
    };

    scala.Function1 sAbs = new AbstractFunction1<Integer, Integer>() {
        @Override
        public Integer apply(Integer v1) {
            return v1 < 0 ? -v1 : v1;
        }
    };

    @Benchmark
    public Object scalaCompose() {
        return package$.MODULE$.compose(sDouble, sAbs).apply(x);
    }

    Function1<Integer, Integer> kDouble = (v1) -> v1 * 2;
    Function1<Integer, Integer> kAbs = (v1) -> v1 < 0 ? -v1 : v1;


    @Benchmark
    public Object kotlinCompose() {
        return func.INSTANCE.compose(kDouble, kAbs).invoke(x);
    }
}

package benchmark.func;

import kotlin.jvm.functions.Function2;
import kscala.func.func;
import org.openjdk.jmh.annotations.*;
import scala.Function1;
import scala.func.package$;
import scala.runtime.AbstractFunction2;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class CurryBenchmark {
    int x = 1;
    int y = 2;

    scala.Function2<Integer, Integer, Integer> scalaSum = new AbstractFunction2<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer v1, Integer v2) {
            return v1 + v2;
        }
    };

    Function2<Integer, Integer, Integer> kotlinSum =  (v1, v2) -> v1 + v2;

    @Benchmark
    public int scalaCurry() {
        return package$.MODULE$.curry(scalaSum).apply(x).apply(y);
    }

    @Benchmark
    public int scalaCurryP() {
        return package$.MODULE$.curry(scalaSum).apply(x).apply(y);
    }

    @Benchmark
    public int kotlinCurry() {
        return func.INSTANCE.curry(kotlinSum).invoke(x).invoke(y);
    }
}

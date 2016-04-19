package benchmark.func;

import jscala.func.F2;
import jscala.func.Func;
import kotlin.jvm.functions.Function2;
import kscala.func.func;
import org.openjdk.jmh.annotations.*;
import scala.func.SFunc$;
import scala.runtime.AbstractFunction2;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class CurryBenchmark {
    int x = 1;
    int y = 2;

    //scala
    scala.Function2<Integer, Integer, Integer> scalaSum = new AbstractFunction2<Integer, Integer, Integer>() {
        @Override
        public Integer apply(Integer v1, Integer v2) {
            return v1 + v2;
        }
    };

    @Benchmark
    public Integer scalaCurry() {
        return SFunc$.MODULE$.curry(scalaSum).apply(x).apply(y);
    }

    //kotlin
    Function2<Integer, Integer, Integer> kotlinSum =  (v1, v2) -> v1 + v2;

    @Benchmark
    public Integer kotlinCurry() {
        return func.INSTANCE.curry(kotlinSum).invoke(x).invoke(y);
    }

    //java
    F2<Integer, Integer, Integer> jSum =  (v1, v2) -> v1 + v2;

    @Benchmark
    public Integer javaCurry() {
        return Func.curry(jSum).apply(x).apply(y);
    }

    @TearDown(Level.Trial)
    public void check() {
        assert 3 == SFunc$.MODULE$.curry(scalaSum).apply(x).apply(y);
        assert 3 == func.INSTANCE.curry(kotlinSum).invoke(x).invoke(y);
        assert 3 == Func.curry(jSum).apply(x).apply(y);
    }
}

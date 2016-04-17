package benchmark.func;

import jscala.func.F1;
import jscala.func.Func;
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

    F1<Integer, Integer> jDouble = (v1) -> v1 * 2;
    F1<Integer, Integer> jAbs = (v1) -> v1 < 0 ? -v1 : v1;

    @Benchmark
    public Object javaCompose() {
        return Func.compose(jDouble, jAbs).apply(x);
    }

    @TearDown(Level.Trial)
    public void check() {
        assert 6 == (Integer) package$.MODULE$.compose(sDouble, sAbs).apply(x);
        assert 6 == func.INSTANCE.compose(kDouble, kAbs).invoke(x);
        assert 6 == Func.compose(jDouble, jAbs).apply(x);
    }
}

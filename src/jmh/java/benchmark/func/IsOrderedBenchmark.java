package benchmark.func;

import gscala.func.GFunc;
import kotlin.jvm.functions.Function2;
import kscala.func.func;
import org.openjdk.jmh.annotations.*;
import scala.func.SFunc$;
import scala.runtime.AbstractFunction2;

import java.util.function.BiFunction;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class IsOrderedBenchmark {

    Integer[] array = {1,2, 3, 4, 5, 6, 7};
    scala.Function2 scalaOrdered = new AbstractFunction2<Integer, Integer, Object>() {
        @Override
        public Object apply(Integer v1, Integer v2) {
            return v1 <= v2;
        }
    };


    Function2<Integer, Integer, Boolean> kotlinOrdered = (v1, v2) -> v1 <= v2;

    @Benchmark
    public boolean scalaIsOrdered() {
        return SFunc$.MODULE$.isSorted(array, scalaOrdered);
    }

    @Benchmark
    public boolean kotlinIsOrdered() {
        return func.INSTANCE.isSorted(array, kotlinOrdered);
    }

    BiFunction<Integer, Integer, Boolean> groovyIsOrdered = (v1, v2) -> v1 <= v2;

    @Benchmark
    public boolean groovyIsOrdered() {
        return GFunc.isSorted(array, groovyIsOrdered);
    }

}

package benchmark;

import kotlin.jvm.functions.Function2;
import kscala.func.func;
import org.openjdk.jmh.annotations.*;
import scala.func.package$;
import scala.runtime.AbstractFunction2;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class FunctionsBenchmark {

    Object[] array = {1,2, 3, 4, 5, 6, 7};

    @Benchmark
    public boolean scalaIsOrdered() {
        return package$.MODULE$.isSorted(array, new AbstractFunction2<Object, Object, Object>() {
            @Override
            public Object apply(Object v1, Object v2) {
                Number n1 = (Number) v1;
                Number n2 = (Number) v2;
                return n1.doubleValue() <= n2.doubleValue();
            }
        });
    }

    @Benchmark
    public boolean kotlinIsOrdered() {
        return func.INSTANCE.isSorted(array, new Function2<Object, Object, Boolean>() {
            @Override
            public Boolean invoke(Object o, Object o2) {
                Number n1 = (Number) o;
                Number n2 = (Number) o2;
                return n1.doubleValue() <= n2.doubleValue();
            }
        });
    }

}

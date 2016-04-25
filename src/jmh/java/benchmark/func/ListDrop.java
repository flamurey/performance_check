package benchmark.func;

import kotlin.jvm.functions.Function1;
import kscala.func.List;
import org.openjdk.jmh.annotations.*;
import scala.func.List$;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class ListDrop {

    Integer[] data = {1, 2, 3, 4, 5, 6, 7, 8};
    List<Integer> list = List.Companion.invoke(data);
    scala.func.List<Integer> scalaList = List$.MODULE$.apply2(data);

    int n = 5;

    private Function1<Integer, Boolean> kDropWhile = (x) -> true;
    private scala.Function1<Integer, Object> sDropWhile = (x) -> true;

    @Benchmark
    public Object kotlinDrop() {
        return list.drop(n);
    }

    @Benchmark
    public Object scalaDrop() {
        return scalaList.drop(n);
    }

    @Benchmark
    public Object kotlinDropWhile() {
        return list.dropWhile(kDropWhile);
    }

    @Benchmark
    public Object scalaDropWhile() {
        return scalaList.dropWhile(sDropWhile);
    }
}

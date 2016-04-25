package benchmark.func;

import kscala.func.List;
import org.openjdk.jmh.annotations.*;
import scala.func.List$;


@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class ListToString {

    Integer[] data = {1, 2, 3, 4, 5, 6, 7, 8};
    List<Integer> list = List.Companion.invoke(data);
    scala.func.List<Integer> scalaList = List$.MODULE$.apply2(data);

    @Benchmark
    public String kotlinByFunctional() {
        return list.toString();
    }

    @Benchmark
    public Object kotlinByOpp() {
        return list.toStringByOOP();
    }

    @Benchmark
    public String scalaByFunctional() {
        return scalaList.toString();
    }
}
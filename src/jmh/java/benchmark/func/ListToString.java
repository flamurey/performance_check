package benchmark.func;

import kscala.func.List;
import org.openjdk.jmh.annotations.*;
import scala.collection.Seq;

import java.util.Arrays;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class ListToString {

    Integer[] data = {1, 2, 3, 4, 5, 6, 7, 8};
    List<Integer> list = List.Companion.invoke(data);


    Seq<Integer> seq = scala.collection.JavaConversions.asScalaBuffer(
            Arrays.asList(data)
    ).seq();

    @Benchmark
    public String byFunctional() {
        return list.toString();
    }

    @Benchmark
    public Object byOpp() {
        return list.toStringByOOP();
    }
}
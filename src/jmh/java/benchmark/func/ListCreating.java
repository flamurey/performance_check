package benchmark.func;

import kscala.func.List;
import org.openjdk.jmh.annotations.*;
import scala.collection.Seq;
import scala.func.List$;

import java.util.Arrays;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class ListCreating {

    Integer[] data = {1, 2, 3, 4, 5, 6, 7 , 8};

    Seq<Integer> seq = scala.collection.JavaConversions.asScalaBuffer(
            Arrays.asList(data)
    ).seq();

    @Benchmark
    public Object kotlin() {
        return List.Companion.invoke(data);
    }

    @Benchmark
    public Object scala() {
        return List$.MODULE$.apply2(data);
    }

    @Benchmark
    public Object scalaFromSeq() {
        return List$.MODULE$.apply(seq);
    }
}

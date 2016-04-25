package benchmark.func;

import kotlin.jvm.functions.Function1;
import kscala.func.List;
import org.openjdk.jmh.annotations.*;
import scala.func.List$;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class ListMap {

    Integer[] data = {1, 2, 3, 4, 5, 6, 7, 8};

    List<Integer> list = List.Companion.invoke(data);
    scala.func.List<Integer> scalaList = List$.MODULE$.apply2(data);

    private Function1<Integer, Integer> kMap = (x) -> x + 2;
    private scala.Function1<Integer, Integer> sMap = (x) -> x + 2;
    private Function<Integer, Integer> jMap = (x) -> x + 2;

    @Benchmark
    public Object kotlinMap() {
        return list.map(kMap);
    }

    @Benchmark
    public Object scalaMap() {
        return scalaList.map(sMap);
    }

    @Benchmark
    public Object kotlinCreateMap() {
        List<Integer> list = List.Companion.invoke(data);
        return list.map(kMap);
    }

    @Benchmark
    public Object scalaCreateMap() {
        scala.func.List<Integer> scalaList = List$.MODULE$.apply2(data);
        return scalaList.map(sMap);
    }

    @Benchmark
    public Object javaCreateMap() {
        return Stream.of(data)
                .map(jMap)
                .collect(Collectors.toList());
    }
}
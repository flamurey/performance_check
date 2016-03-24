package jmh;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JNumberFinder {

    public static int findByForCicle(int[] data, int number) {
        int res = 0;
        for (int aData : data) {
            if (aData < number) res += aData;
        }
        return res;
    }

    public static int findByIntStream(int[] data, int number) {
        IntStream stream = IntStream.of(data);
        return stream.filter(x -> x < number).sum();
    }

    public static int findByIntStreamWithLambda(int[] data, int number) {
        IntStream stream = IntStream.of(data);
        return stream.filter(x -> x < number).reduce(0, Integer::sum);
    }

    public static int findByTypedStream(Integer[] data, int number) {
        Stream<Integer> stream = Stream.of(data);
        return stream.filter(x -> x < number).reduce(0, Integer::sum);
    }

    /**
     * Релизует синтаксис котлина.
     * В плане производительности (выполнении действий jvm) сравнение не често.
     * TODO: переписать чтоб была также логика с тем же ситксисом.
     */
    public static int findByListAndLambda(List<Integer> data, int number) {
        return data.stream().filter(x -> number < x).reduce(0, Integer::sum);
    }

}

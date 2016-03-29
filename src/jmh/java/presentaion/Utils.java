package presentaion;

import java.util.stream.IntStream;

public class Utils {
    static Object doWork() {
        return IntStream.range(0, 1000)
                .filter(x -> x % 2 == 0)
                .sum();
    }
}

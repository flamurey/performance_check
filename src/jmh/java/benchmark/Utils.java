package benchmark;

import java.util.function.IntFunction;

public class Utils {
    static int[] createData(int size, IntFunction<Integer> generator) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = generator.apply(i);
        }
        return data;
    }

    public static Integer[] convertIntArrayToWrapped(int data[]) {
        Integer[] res = new Integer[data.length];
        for (int i = 0; i < data.length; i++) {
            res[i] = data[i];
        }
        return res;
    }
}

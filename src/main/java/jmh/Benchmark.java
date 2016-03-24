package jmh;

import java.util.List;

public interface Benchmark {

    interface FindAllSmallerNumberAndSum {
        int run(int[] data, int number);
    }

    interface WrappedFindAllSmallerNumberAndSum {
        int run(Integer[] data, int number);
    }

    interface CreateList {
        List<Integer> run(int[] data);
    }
}

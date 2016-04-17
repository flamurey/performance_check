package jscala.func;

public class Func {
    public static <A, B, C> F1<A, F1<B, C>> curry(F2<A, B, C> f) {
        return a -> b -> f.apply(a, b);
    }

    public static <A, B, C, R> F1<A, F1<B, F1<C, R>>> curry3(F3<A, B, C, R> f) {
        return a -> b -> c -> f.apply(a, b, c);
    }

    public static <A, B, C> F2<A, B, C> uncurry(F1<A, F1<B, C>> f) {
        return (a, b) -> f.apply(a).apply(b);
    }

    public static <A, B, C> F1<A, C> compose(F1<A, B> g, F1<B, C> f) {
        return a -> f.apply(g.apply(a));
    }

    public static void main(String[] args) {
        F1<Integer, Integer> doubleF = x -> x * 2;
        F1<Integer, Integer> abs = x -> x < 0 ? -x : x;
        F1<Integer, Integer> sumAndPow = compose(abs, doubleF);
        System.out.println("sumAndPow -2: " + sumAndPow.apply(-2));
        System.out.println("sumAndPow 2: " + sumAndPow.apply(2));
        F2<Integer, Integer, Integer> sum = (x, y) -> x + y;
        F1<Integer, F1<Integer, Integer>> curried = curry(sum);
        F1<Integer, Integer> addToOne = curried.apply(1);
        F1< Integer, Integer> addToTwo = curried.apply(2);
        System.out.println("addToOne + 2: " + addToOne.apply(2));
        System.out.println("addToTwo + 2: " + addToTwo.apply(2));
        F2<Integer, Integer, Integer> uncurried = uncurry(curried);
        System.out.println("uncurryed 1 + 2: " + uncurried.apply(1, 2));

        F3<Long, Long, Long, Double> sumPow = (a, b, c) -> Math.pow(a + b, c);
        F1<Long, F1<Long, F1<Long, Double>>> longF1F1 = curry3(sumPow);
        System.out.println(longF1F1.apply(1L).apply(2L).apply(3L));
    }
}

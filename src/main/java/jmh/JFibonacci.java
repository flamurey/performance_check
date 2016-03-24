package jmh;

public class JFibonacci {
    public static long fibRec(int n, long a, long b) {
        return n > 1 ? fibRec(n - 1, b, a + b) : b;
    }

    public static long fastLoop(int n, long a, long b) {
        while (n > 1) {
            long c = a + b;
            a = b;
            b = c;
            n--;
        }
        return b;
    }
}

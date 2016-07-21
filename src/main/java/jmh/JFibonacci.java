package jmh;

import java.math.BigInteger;

public class JFibonacci {
    public static long fibRec(int n, long a, long b) {
        return n > 1 ? fibRec(n - 1, b, a + b) : b;
    }

    public static BigInteger fastLoop(int n, BigInteger a, BigInteger b) {
        while (n > 1) {
            BigInteger c = a.add(b);
            a = b;
            b = c;
            n--;
        }
        return b;
    }
}

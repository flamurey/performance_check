package jmh;

import java.math.BigInteger;

public class FibonacciLogN {
    private static BigInteger big(long value) {
        return BigInteger.valueOf(value);
    }
    private final static BigInteger[][] A = {{ big(1), big(1)}, {big(1), big(0)}};

    //2*2 matrix
    static BigInteger[][] multiply(BigInteger[][] X, BigInteger[][] Y) {
        BigInteger[][] response = new BigInteger[2][2];
        response[0][0] = X[0][0].multiply(Y[0][0]).add(X[0][1].multiply(Y[1][0]));
        response[0][1] = X[0][0].multiply(Y[0][1]).add(X[0][1].multiply(Y[1][1]));
        response[1][0] = X[1][0].multiply(Y[0][0]).add(X[1][1].multiply(Y[1][0]));
        response[1][1] = X[1][0].multiply(Y[0][1]).add(X[1][1].multiply(Y[1][1]));
        return response;
    }

    static BigInteger[][] power(BigInteger[][] X, int N) {
        if (N == 1) {
            return X;
        } else {

            BigInteger[][] X2 = power(X, N / 2);
            BigInteger[][] res = multiply(X2, X2);
            if (N % 2 == 1) {
                res = multiply(res, A);
            }
            return res;
        }
    }

    public static BigInteger get(int n) {
        return power(A, n - 1)[0][0];
    }

    public static void main(String[] args) {
        int n = 145;
        System.out.println(get(n));
        System.out.println(JFibonacci.fastLoop(n, big(0), big(1)));
    }
}
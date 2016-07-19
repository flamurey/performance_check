package jmh;

public class FibonacciLogN {
    private final static long[][] A = {{1, 1}, {1, 0}};

    //2*2 matrix
    static long[][] multiply(long[][] X, long[][] Y) {
        long[][] response = new long[2][2];
        response[0][0] = X[0][0] * Y[0][0] + X[0][1] * Y[1][0];
        response[0][1] = X[0][0] * Y[0][1] + X[0][1] * Y[1][1];
        response[1][0] = X[1][0] * Y[0][0] + X[1][1] * Y[1][0];
        response[1][1] = X[1][0] * Y[0][1] + X[1][1] * Y[1][1];
        return response;
    }

    static long[][] power(long[][] X, int N) {
        if (N == 1) {
            return X;
        } else {

            long[][] X2 = power(X, N / 2);
            long[][] res = multiply(X2, X2);
            if (N % 2 == 1) {
                res = multiply(res, A);
            }
            return res;
        }
    }

    public static long get(int n) {
        return power(A, n - 1)[0][0];
    }

    public static void main(String[] args) {
        int n = 145;
        System.out.println(get(n));
        System.out.println(JFibonacci.fastLoop(n, 0, 1));
    }
}
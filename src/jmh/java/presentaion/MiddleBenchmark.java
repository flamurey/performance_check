package presentaion;

public class MiddleBenchmark {
    static final int N = 10000;
    public static void main(String[] args) {
        Utils.doWork();
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            Object result = Utils.doWork();
        }
        float time = (System.nanoTime() - start)/ 1000;
        System.out.println("Total time:" + time);
        System.out.println("Time on operation:" + time/ N);
    }
}

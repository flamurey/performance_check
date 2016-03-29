package presentaion;

public class JuniorBenchmark {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Object result = Utils.doWork();
        long time = System.currentTimeMillis() - start;
        System.out.println("Total time:" + time);
        System.out.println(result);
    }
}



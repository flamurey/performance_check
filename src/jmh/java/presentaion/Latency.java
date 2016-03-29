package presentaion;

public class Latency {
    public static void main(String[] args) {
        long start = System.nanoTime();
        long time = System.nanoTime() - start;
        System.out.println("Total time:" + time);
    }
}

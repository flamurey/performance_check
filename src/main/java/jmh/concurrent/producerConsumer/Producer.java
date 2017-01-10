package jmh.concurrent.producerConsumer;

public class Producer extends Thread {
    private final int[] data;
    private Buffer buffer;

    public Producer(Buffer b, int[] data) {
        buffer = b;
        this.data = data;
    }
    public void run() {
        for (int value: data) {
            buffer.put(value);
        }
    }
}
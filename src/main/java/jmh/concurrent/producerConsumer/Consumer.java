package jmh.concurrent.producerConsumer;

public class Consumer extends Thread {
    private Buffer buffer;
    private boolean closed;

    public Consumer(Buffer c) {
        buffer = c;
    }

    public void close() {
        this.closed = true;
    }

    public void run() {
        while (!closed) buffer.get();
        System.out.println("Consumer closed");
    }
}
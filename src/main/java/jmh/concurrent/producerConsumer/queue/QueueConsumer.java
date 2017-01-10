package jmh.concurrent.producerConsumer.queue;

import java.util.concurrent.BlockingQueue;

public class QueueConsumer extends Thread {
    private final BlockingQueue sharedQueue;
    private boolean closed;

    public QueueConsumer (BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    public void close() {
        this.closed = true;
    }

    @Override
    public void run() {
        while(!closed){
            try {
                sharedQueue.take();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

package jmh.concurrent.producerConsumer.queue;

import java.util.concurrent.BlockingQueue;

public class QueueProducer extends Thread {

    private final BlockingQueue sharedQueue;
    private final int[] data;

    public QueueProducer(BlockingQueue sharedQueue, int[] data) {
        this.sharedQueue = sharedQueue;
        this.data = data;
    }

    @Override
    public void run() {
        for(int value: data){
            try {
                sharedQueue.put(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}



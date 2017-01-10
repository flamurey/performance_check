package benchmark.concurrent;

import jmh.concurrent.producerConsumer.Buffer;
import jmh.concurrent.producerConsumer.Consumer;
import jmh.concurrent.producerConsumer.Producer;
import jmh.concurrent.producerConsumer.queue.QueueConsumer;
import jmh.concurrent.producerConsumer.queue.QueueProducer;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
public class ConsumerProducer {

    int n = 1000_000;

    int[] data = IntStream.range(0, n).toArray();

    Buffer buffer;
    Producer p;
    Consumer c;

    BlockingQueue sharedQueue;
    QueueProducer queueProducer;
    QueueConsumer queueConsumer;

    @Setup(Level.Invocation)
    public void setup() {
        buffer = new Buffer();
        p = new Producer(buffer, data);
        c = new Consumer(buffer);

        sharedQueue = new ArrayBlockingQueue(1);
        queueProducer = new QueueProducer(sharedQueue, data);
        queueConsumer = new QueueConsumer(sharedQueue);
    }

    @Benchmark
    public int bySynchronizations() throws InterruptedException {
        p.start();
        c.start();
        p.join();
        c.close();
        return n;
    }

    @Benchmark
    public int byLinkedBlockQueue() throws InterruptedException {
        queueProducer.start();
        queueConsumer.start();
        queueProducer.join();
        queueConsumer.close();
        return n;
    }

}

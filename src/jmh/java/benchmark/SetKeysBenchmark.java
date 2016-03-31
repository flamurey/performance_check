package benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.*;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
public class SetKeysBenchmark {

    private HashSet<String> hashSet;
    private ArrayList<String> arrayList;
    private List<String> data;
    private Random random;

    @Setup
    public void setup() {
        data = Arrays.asList("qwer", "232");
        hashSet = new HashSet<>(data);
        arrayList = new ArrayList<>(data);
        random = new Random();
    }

    String key() {
        String key = data.get(random.nextInt(data.size()));
        return new String(key);
    }

    @Benchmark
    public boolean hashSet() {
        return hashSet.contains(key());
    }

    @Benchmark
    public boolean arraList() {
        return arrayList.contains(key());
    }
}

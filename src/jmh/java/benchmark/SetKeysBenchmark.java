package benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.*;

@State(Scope.Benchmark)
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
        return data.get(random.nextInt(data.size()));
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

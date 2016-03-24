package puzzles;

import java.lang.reflect.Array;
import java.util.Collection;

public class Generic {
    public static void main(String[] args) {
        test1();
    }

    static void test1() {

        Wrapper nWrapper = new Wrapper<Integer>(111);
// #1

// Unchecked warning at compile-time and heap pollution at runtime
        Wrapper<String> sWrapper = nWrapper;
// #2
        String str = sWrapper.getData();

        //Object[] arr = new Wrapper<Long>[5];

        Object x[] = new String[3];
        f(x);
        x[0] = 0;

        Wrapper<String>[] a = (Wrapper<String>[]) Array.newInstance(Wrapper.class, 10);
        Object[] objArray = (Object[]) a;
        objArray[0] = new Object(); // Will throw a java.lang.ArrayStoreExceptionxception
        a[0] = new Wrapper<String>(); // OK. Checked by compiler

        Source<? extends Object> s = new Source<String>();

    }

    static void f(Object[] data) {
        if (data.length > 0)
            data[0] = 1;
    }

    void copyAll(Collection<Object> to, Collection<String> from) {
        to.addAll(from);
    }
}

class Wrapper<T> {
    T data;

    Wrapper() {

    }

    Wrapper (T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

class Source<T> {

}
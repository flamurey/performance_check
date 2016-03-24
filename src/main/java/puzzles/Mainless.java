package puzzles;

public class Mainless {
    public static void main(String[] args) {

    }

    void test1 () {
        int i1 = -128;
        int i2 = 128;
        byte b1 = (byte) i1;
        byte b2 = (byte) i2;
        System.out.println(b1 == b2);
    }

    /*void test2 () {
        byte b1 = 127;
        byte b2 = -b1; //compilation error
        System.out.println(b1 == -b2);
    }*/

    void test3 () {
        byte b = 2;
        int i = 3;
        long l = 4;
        float f = 5;
        double d = 6;

        l = l / 2;
    }

    void test4() {
        int v = 34;
        int v1s = ~v;
        v1s ++;
        System.out.println(v1s + v == 0);
    }

    static void test5() {
        System.out.println(34 << 33 == 34 << 1);
    }

    void test6() {
        while (true) {
            if (true) break;
            System.out.println("3");
        }
    }

    void tewt7() {
        MathUtil7.max();
    }
}

class A {
    public A() {}
    public A(int i) {}
    int f1;
    static void f() {}
    void m() {}
}

class B extends A {

}

class MathUtil7 {
    public static void max(double...num) {
        System.out.println("Double");
    }

    public static void max(int...num) {
        System.out.println("Int");
    }
}
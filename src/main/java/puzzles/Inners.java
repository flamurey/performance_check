package puzzles;

public class Inners {
    public static void main(String[] args) throws ClassNotFoundException {
        Outer outer = new Outer();
        Class c = Class.forName("puzzles.Outer$Nested");
        System.out.println(c.toString());

    }
}

class Outer {
    private String outerMessage = "OUTER";

    private static String staticOuterMessage = "STATIC";

    Outer() {
        Inner1 in = new Inner1();
        System.out.println(in.message);
    }

    private class Inner1 {
        {
            System.out.println(outerMessage);
        }

        private String message = "INNER";
    }

    static class Nested {
        static {
            System.out.println(staticOuterMessage);
        }
    }

}

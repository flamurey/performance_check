package puzzles;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
    public static void main(String[] args) {
        Path p1 = Paths.get("d1/p1");
        Path p2 = Paths.get("p2");
        System.out.println(p1.relativize(p2));
        System.out.println(p2.relativize(p1));
    }
}

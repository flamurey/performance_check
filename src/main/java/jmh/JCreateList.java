package jmh;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JCreateList {
    public static List<Integer> createByStream(int[] data) {
        return IntStream.of(data).boxed().collect(Collectors.toList());
    }

    public static List<Integer> createByForCycle(int[] data) {
        List<Integer> intList = new ArrayList<>(data.length);
        for (int index = 0; index < data.length; index++)
        {
            intList.add(data[index]);
        }
        return intList;
    }

    public static List<Integer> createByForEnhanced(int[] data) {
        List<Integer> intList = new ArrayList<>(data.length);
        for (int aData : data) {
            intList.add(aData);
        }
        return intList;
    }

    public static List<Integer> createByForCycleAsLinkedList(int[] data) {
        List<Integer> intList = new ArrayList<>(data.length);
        for (int index = 0; index < data.length; index++)
        {
            intList.add(data[index]);
        }
        return intList;
    }
}
package gscala.func

import groovy.transform.TailRecursive

import java.util.function.BiFunction

class GFunc {
    def static <T> Boolean isSorted(T[] array, BiFunction<T,T, Boolean> ordered) {
        return isSortedLoop(array, ordered, 0)
    }

    @TailRecursive
    def static <T> Boolean isSortedLoop (T[] array, BiFunction<T,T, Boolean> ordered, int index) {
        if (index >= array.length -1) return true
        else if (ordered.apply(array[index], array[index + 1])) return isSortedLoop(array, ordered, index + 1)
        else return false
    }
}
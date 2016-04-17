package jmh

import java.util.stream.IntStream

class KNumberFinder {
  companion object {
    fun findByListAndLambda(data: List<Int>, number: Int): Int = data
      .filter { it < number }
      .sum()

    /*
    performance issue: spread operator '*' compiled into Arrays.copy
     */
    fun findByStream(data: IntArray, number: Int): Int = IntStream.of(*data)
      .filter { it < number }
      .sum()

    fun findByLazySequence(data: IntArray, number: Int): Int = data.asSequence()
            .filter { it < number }
            .sum()
  }
}
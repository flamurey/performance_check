package scala

package object func {
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    def loop(index: Int): Boolean =
      if (index >= as.length - 1) true
      else if (ordered(as(index), as(index + 1))) loop(index + 1)
      else false

    loop(0)
  }

  def main(args: Array[String]) {
    println(isSorted(Array(4, 2, 3, 4, 6), (x: Int, y: Int) => x <= y))
  }
}

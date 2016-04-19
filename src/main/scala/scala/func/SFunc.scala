package scala.func

object SFunc {
  def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
    def loop(index: Int): Boolean =
      if (index >= as.length - 1) true
      else if (ordered(as(index), as(index + 1))) loop(index + 1)
      else false

    loop(0)
  }

  def partial1[A, B, C](a: A, f: (A, B) => C): B => C =
    (b: B) => f(a, b)

  def curry[A, B, C](f: (A, B) => C): A => (B => C) =
    (a: A) => (b: B) => f(a, b)

  def curryP[A, B, C](f: (A, B) => C): A => (B => C) =
    (a: A) => partial1(a: A, f)

  def uncurry[A, B, C](f: A => B => C): (A, B) => C =
    (a: A, b: B) => f(a)(b)

  def compose[A, B, C](g: A => B, f: B => C): A => C =
    (a: A) => f(g(a))

  def main(args: Array[String]) {
    println(isSorted(Array(4, 2, 3, 4, 6), (x: Int, y: Int) => x <= y))
    def double = (x: Int) => x * 2
    def abs = (x: Int) => if (x < 0) -x else x
    def sumAndPow = compose(abs, double)
    println("sumAndPow -2: " + sumAndPow(-2))
    println("sumAndPow 2: " + sumAndPow(2))
    def sum = (x: Int, y: Int) => x + y
    def curried = curry(sum)
    def addToOne = curried(1)
    def addToTwo = curried(2)
    println("addToOne + 2: " + addToOne(2))
    println("addToTwo + 2: " + addToTwo(2))
    def uncurried = uncurry(curried)
    println("uncurryed 1 + 2: " + uncurried(1, 2))
  }
}

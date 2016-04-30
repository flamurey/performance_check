package jmh

object Scala {
  def fibRec(n: Int, a: Long = 0, b: Long = 1): Long =
    if (n > 1) fibRec(n - 1, b, a + b) else b

  def whileLoop(n: Int): Int = {
    var i = 0
    var x = 0
    while (i < n) {
      x += 1
      i += 1
    }
    x
  }

  def forLoop(n: Int) = {
    var x = 0
    for (i <- 0 to n) {
      x += 1
    }
    x
  }
}
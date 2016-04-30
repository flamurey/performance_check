package jmh

object Scala {
  def fibRec(n: Int, a: Long = 0, b: Long = 1): Long =
    if (n > 1) fibRec(n - 1, b, a + b) else b

  def whileLoop(n: Int):Int = {
    var i,j,k = 0
    var x = 0
    while(i < 10000) {
      while(j < 1000) {
        while(k < n) {
          x += 1
          k += 1
        }
        k = 0
        j += 1
      }
      j = 0
      i += 1
    }
    k
  }

  def forLoop(n: Int) = {
    var x = 0
    for(i <- 0 to 10000; j <- 0 to 1000; k <- 0 to n) {
      x += 1
    }
    x
  }
}
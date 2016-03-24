package jmh

class KFibonacci {
  companion object {
    tailrec fun fibRec(n: Long, a: Long, b: Long): Long =
      if (n > 1) fibRec(n - 1, b, a + b) else b
  }
}
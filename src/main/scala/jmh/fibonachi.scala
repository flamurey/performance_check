package jmh

import scala.annotation.tailrec

object Scala {
  @annotation.tailrec
  def fibRec(n: Int, a: Long = 0, b: Long = 1): Long =
    if (n > 1) fibRec(n - 1, b, a + b) else b
}
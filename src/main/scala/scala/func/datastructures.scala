package scala.func

sealed trait List[+A] {
  def tail(): List[A] = this match {
    case Nil => Nil
    case Cons(x, xs) => xs
  }

  def setHead[B >: A](newHead: B) = this match {
    case Nil => Cons(newHead, Nil)
    case Cons(x, xs) => Cons(newHead, xs)
  }
}

case object Nil extends List[Nothing]

case class Cons[+A](head: A, listTail: List[A]) extends List[A]

object List {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def apply2[A](as: Array[A]): List[A] = {
    def loop(acc: List[A], index: Int): List[A] =
      if (index < 0) acc
      else loop(Cons(as(index), acc), index - 1)
    loop(Nil, as.length - 1)
  }
}

object A {
  def main(args: Array[String]) {
    val l = List(1, 2, 3, 4, 5)
    println(l.tail())
    l.setHead("132")
    println(l.setHead(l))
  }
}

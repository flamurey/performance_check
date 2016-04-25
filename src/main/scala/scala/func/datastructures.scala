package scala.func

import scala.annotation.tailrec

sealed trait List[+A] {
  def tail(): List[A] = this match {
    case Cons(x, xs) => xs
    case _ => this
  }

  @tailrec final def drop(n: Int): List[A] =
    this match {
      case Cons(h, t) if n > 0 => t.drop(n - 1)
      case _ => this
    }

  def dropWhile(f: A => Boolean): List[A] =
    this match {
      case Cons(h, t) if f(h) => t.dropWhile(f)
      case _ => this
    }

  def appendSimple[B >: A](list: List[B]): List[B] = list match {
    case Nil => this
    case Cons(x, xs) => Cons(x, this.append(xs))
  }

  def append[B >: A](list: List[B]): List[B] = {
    val self: List[B] = this
    list.foldRight(self)((item, list) => Cons(item, list))
  }

  def map[B](f: A => B): List[B] = {
    val acc: List[B] = Nil
    this.foldRight(acc)((x, list) => Cons(f(x), list))
  }

  def foldRightSimple[B](z: B)(f: (A, B) => B): B =
    this match {
      case Cons(x, xs) => f(x, xs.foldRightSimple(z)(f))
      case _ => z
    }

  @tailrec
  final def foldLeft[B](z: B)(f: (B, A) => B): B =
    this match {
      case Cons(x, xs) => xs.foldLeft(f(z, x))(f)
      case _ => z
    }

  def reverse(): List[A] = {
    val tail: List[A] = Nil
    foldLeft(tail)((acc, x) => Cons(x, acc))
  }

  def foldRight[B](z: B)(f: (A, B) => B): B =
    reverse().foldLeft(z)((acc, item) => f(item, acc))

  def length(): Int = foldLeft(0)((size, a) => size + 1)

  def init(): List[A] = {
    def loop(l: List[A]): List[A] = {
      l match {
        case Nil => Nil
        case Cons(h, Nil) => Nil
        case Cons(h, t) => Cons(h, loop(t))
      }
    }
    loop(this)
  }

  def setHead[B >: A](newHead: B) = this match {
    case Nil => Cons(newHead, Nil)
    case Cons(x, xs) => Cons(newHead, xs)
  }

  override def toString: String = {
    def loop(delimiter: String, s: StringBuilder, tail: List[A]): String = tail match {
      case Nil => s.toString
      case Cons(x, xs) =>
        s.append(delimiter)
        s.append(x)
        loop(",", s, xs)
    }
    val list = loop("", new StringBuilder(), this)
    s"[$list]"
  }
}

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tailList: List[A]) extends List[A]

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

  def sum2(ns: List[Int]) = ns.foldLeft(0)(_ + _)

  def product2(ns: List[Int]) = ns.foldLeft(1)(_ * _)

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
    val l = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val d = l
      .tail()
      .drop(2)
      .setHead(6)
      .dropWhile(_ > 5)
      .append(List(2, 3, 4))
      .init()
      .reverse()
      .map(_ - 1)
    println(d)
  }
}

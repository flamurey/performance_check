package kscala.func

sealed class List<out T> {
    object Nil : List<Nothing>() {
        override fun asString(delimiter: String, s: StringBuilder) = s.toString()
    }

    class Cons<out T>(val head: T, val tail: List<T>) : List<T>() {
        override fun asString(delimiter: String, s: StringBuilder): String {
            s.append(delimiter)
            s.append(head)
            return tail.asString(",", s)
        }
    }

    final fun drop(n: Int): List<T> {
        tailrec fun loop(nn: Int, l: List<T>): List<T> =
                if (nn == 0) l
                else when (l) {
                    is Cons<T> -> loop(nn - 1, l.tail)
                    else -> Nil
                }
        return loop(n, this)
    }

    fun dropWhile(f: (T) -> Boolean): List<T> {
        tailrec fun loop(l: List<T>): List<T> =
                when (l) {
                    is Nil -> Nil
                    is Cons<T> ->
                        if (f(l.head)) loop(l.tail)
                        else l
                }
        return loop(this)
    }

    fun <B> foldLeft(init: B, f: (B, T) -> B): B {
        tailrec fun loop(l: List<T>, acc: B): B =
                when (l) {
                    is Cons<T> -> loop(l.tail, f(acc, l.head))
                    else -> acc
                }
        return loop(this, init)
    }

    fun reverse(): List<T> = foldLeft(Nil as List<T>) { acc, x -> Cons(x, acc) }

    fun <B> foldRight(z: B, f: (T, B) -> B): B = reverse().foldLeft(z) { acc, x -> f(x, acc) }

    fun <B> map(f: (T) -> B): List<B> = foldRight(Nil as List<B>) { x, acc -> Cons(f(x), acc) }

    fun init(): List<T> = when (this) {
        is Cons<T> ->
            if (this.tail === Nil) Nil
            else Cons(this.head, this.tail.init())
        else -> Nil
    }

    override fun toString(): String {
        tailrec fun loop(delimiter: String, s: StringBuilder, l: List<T>): String =
                when (l) {
                    is Nil -> s.toString()
                    is Cons<T> -> {
                        s.append(delimiter)
                        s.append(l.head)
                        loop(",", s, l.tail)
                    }
                }

        val list = loop("", StringBuilder(), this)
        return "[$list]"
    }

    abstract fun asString(delimiter: String, s: StringBuilder): String

    fun toStringByOOP(): String = "[${asString("", StringBuilder())}]"

    fun tail(): List<T> = when (this) {
        is Nil -> Nil
        is Cons<T> -> this.tail
    }

    companion object {
        operator fun <T> invoke(vararg data: T): List<T> {
            tailrec fun loop(acc: List<T>, index: Int): List<T> =
                    if (index < 0) acc
                    else loop(Cons(data[index], acc), index - 1)
            return loop(Nil, data.size - 1)
        }
    }
}

fun <T> List<T>.setHead(head: T): List<T> = when (this) {
    is List.Nil -> List.Nil
    is List.Cons -> List.Cons(head, this.tail)
}

fun <T> List<T>.append(l: List<T>): List<T> = l.foldRight(this) { x, acc -> List.Cons(x, acc) }

fun main(args: Array<String>) {
    val l = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val d = l
            .tail()
            .drop(2)
            .setHead(6)
            .dropWhile { it > 5 }
            .append(List(2, 3, 4))
            .init()
            .reverse()
            .map { it - 1 }
    println(d)
}
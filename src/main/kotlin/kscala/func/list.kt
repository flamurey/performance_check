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

fun main(args: Array<String>) {
    val l = List(1, 2, 3, 4, 5)
    println(l.tail())
    l.setHead("132")
    println(l.setHead(l))
    println(l.setHead(l).toStringByOOP())
}
package kscala.func

sealed class List<out T> {
    object Nil : List<Nothing>()

    class Cons<out T>(val head: T, val tail: List<T>) : List<T>()

    override fun toString(): String {
        tailrec fun loop(delimeter: String, s: StringBuilder, l: List<T>): String =
            when (l) {
                is Nil -> s.toString()
                is Cons<T> ->  {
                    s.append(delimeter)
                    s.append(l.head)
                    loop(",", s, l.tail)
                }
            }
        val list = loop("", StringBuilder(), this)
        return "[$list]"
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

fun main(args: Array<String>) {
    val l = List(1, 2, 3, 4, 5)
    println(l.toString())
}
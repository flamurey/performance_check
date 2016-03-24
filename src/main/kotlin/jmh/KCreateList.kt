package jmh

class KCreateList {
    companion object {
        fun createToList(data: IntArray):List<Int> = data.toList()
    }
}

fun main(args : Array<String>) {
    val i: Int? = null
    println(2*1 + 1)
    println(i ?: 2 + 1)
}
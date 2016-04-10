package kscala.func

object func {
    fun <T> isSorted(array: Array<T>, ordered: (T, T) -> Boolean): Boolean {
        fun loop(index: Int): Boolean =
                if (index >= array.size - 1) true
                else if (ordered(array[index], array[index + 1])) loop(index + 1)
                else false
        return loop(0)
    }

}
fun main(args: Array<String>) {
    println(func.isSorted(arrayOf(1, 2, 3, 4, 5, 6), { x, y -> x <= y }))
}

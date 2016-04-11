package kscala.func

object func {
    fun <T> isSorted(array: Array<T>, ordered: (T, T) -> Boolean): Boolean {
        fun loop(index: Int): Boolean =
                if (index >= array.size - 1) true
                else if (ordered(array[index], array[index + 1])) loop(index + 1)
                else false
        return loop(0)
    }

    fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C =
            { a: A -> { b: B -> f (a, b) } }

    fun <A, B, C> uncurry(f: (A) -> (B) -> C) : (A, B) -> C =
            { a: A, b: B -> f(a)(b)}

    fun <A, B, C> compose(g: (A) -> B, f: (B) -> C) : (A) -> C =
            {a: A -> f(g(a))}
}

fun main(args: Array<String>) {
    println(func.isSorted(arrayOf(1, 2, 3, 4, 5, 6), { x, y -> x <= y }))
    val double = {x: Int -> x * 2 }
    val abs = {x: Int -> if (x < 0) -x else x}
    val sumAndPow = func.compose(abs, double)
    println("sumAndPow -2: " + sumAndPow(-2))
    println("sumAndPow 2: " + sumAndPow(2))
    val sum = {x: Int, y: Int -> x + y }
    val curried = func.curry(sum)
    val addToOne = curried(1)
    val addToTwo = curried(2)
    println("addToOne + 2: " + addToOne(2))
    println("addToTwo + 2: " + addToTwo(2))
    val uncurried = func.uncurry(curried)
    println("uncurryed 1 + 2: " + uncurried(1, 2))
}

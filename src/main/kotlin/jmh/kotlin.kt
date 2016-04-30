package jmh


object Kotlin {
    @JvmStatic
    fun repeatLoop(n: Int): Int {
        var x = 0
        repeat(n) {
            x++
        }
        return x
    }

    @JvmStatic
    fun rangeLoop(n: Int) : Int {
        var x = 0
        for (i in 0..n) {
            x++
        }
        return x
    }
}
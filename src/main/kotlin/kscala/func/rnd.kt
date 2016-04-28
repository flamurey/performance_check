package kscala.func

import kscala.func.Rnd.Companion.rnd

interface Rnd {
    fun nextInt(): Pair<Int, Rnd>
    fun nonNegativeInt(): Pair<Int, Rnd> {
        tailrec fun loop(rnd: Rnd): Pair<Int, Rnd> {
            val pair = rnd.nextInt()
            return if (pair.first < 0) loop(pair.second) else pair
        }
        return loop(this)
    }
    companion object {
        fun rnd(seed:Long = System.currentTimeMillis()):Rnd = RndImpl(seed)
    }
}

internal class RndImpl(val seed: Long) : Rnd {
    override fun nextInt(): Pair<Int, Rnd> {
        val newSeed = (seed * 0x5DEECE66DL + 0xBL) and 0xFFFFFFFFFFFFL
        val nextRnd = RndImpl(newSeed)
        val n = (newSeed ushr 16).toInt()
        return Pair(n, nextRnd)
    }
}

fun main(args: Array<String>) {
    var rnd = rnd(3)
    repeat(10) {
        val (nextInt, nextRnd) = rnd.nonNegativeInt()
        println(nextInt)
        rnd = nextRnd
    }
}
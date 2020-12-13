package com.lewilewi.AoC2020

public class Day13(input: String) : IAoC {
    val arrival = input.split("\n")[0].toInt()
    val busses = input.split("\n")[1].split(",").filter { it != "x" }.map { it.toLong() }
    val busses2 = input.split("\n")[1].split(",")

    override fun Result1(): String {
        var earliestBus = 0L
        var waitingTime = Long.MAX_VALUE
        for (bus in busses) {
            val wait = (arrival % bus) * -1 + bus
            if (wait < waitingTime) {
                earliestBus = bus
                waitingTime = wait
            }
        }
        return (waitingTime * earliestBus).toString()
    }

    // chinese remainder algorithm copied from here
    // https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
    fun multInv(a: Long, b: Long): Long {
        if (b == 1L) return 1L
        var aa = a
        var bb = b
        var x0 = 0L
        var x1 = 1L
        while (aa > 1L) {
            val q = aa / bb
            var t = bb
            bb = aa % bb
            aa = t
            t = x0
            x0 = x1 - q * x0
            x1 = t
        }
        if (x1 < 0) x1 += b
        return x1
    }

    fun chineseRemainder(n: LongArray, a: LongArray): Long {
        val prod = n.fold(1L) { acc, i -> acc * i }
        var sum = 0L
        for (i in 0 until n.size) {
            val p = prod / n[i]
            sum += a[i] * multInv(p, n[i]) * p
        }
        return sum % prod
    }

    override fun Result2(): String {
        var bussesMods = LongArray(busses.size)
        for (bus in 0..busses.lastIndex)
            bussesMods[bus] = busses[bus] - busses2.indexOf(busses[bus].toString())

        return chineseRemainder(busses.toLongArray(), bussesMods).toString()
    }
}
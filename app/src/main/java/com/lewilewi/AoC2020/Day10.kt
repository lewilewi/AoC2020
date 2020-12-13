package com.lewilewi.AoC2020

import java.io.InputStreamReader

public class Day10(input: String) : IAoC {
    val adapters = input.split("\n").map { it.toInt() }.toSortedSet()

    private fun runIt(ad: Set<Int>): List<Int> {
        var diffs = mutableListOf<Int>()
        var jolt = 0
        for (adapter in ad) {
            diffs.add(adapter - jolt)
            jolt = adapter
        }
        return diffs
    }

    override fun Result1(): String {
        var newAdapters = adapters.toMutableSet()
        newAdapters.add(newAdapters.max()?.plus(3))
        val diffs = runIt(newAdapters)
        return (diffs.count({ it == 1 }) * diffs.count({ it == 3 })).toString()
    }

    // for this I had to get help in the internet, my initial recursion would still run at xmas next year
    private fun traverse(ad: Set<Int>, i: Long, solutions: MutableMap<Long, Long>): Long {
        if (i == ad.size.toLong() - 1)
            return 1L

        if (solutions.contains(i))
            return solutions.get(i)!!

        var count = 0L
        for (j in 1.toLong() + i until ad.size) {
            if (ad.elementAt(j.toInt()) - ad.elementAt(i.toInt()) < 4)
                count += traverse(ad, j, solutions)

        }
        solutions[i] = count
        return count
    }

    override fun Result2(): String {
        var newAdapters = adapters.toMutableSet()
        newAdapters.add(newAdapters.max()?.plus(3))
        newAdapters.add(0)
        var solutions = mutableMapOf<Long, Long>()
        return traverse(newAdapters.toSortedSet(), 0L, solutions).toString()
    }
}
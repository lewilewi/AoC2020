package com.lewilewi.AoC2020

import java.io.InputStreamReader

public class Day10(input : String) : IAoC {
    val adapters = input.split("\n").map{it.toInt()}.toSortedSet()
    var recursionLevel = 0
    var solutions = mutableSetOf<Set<Int>>()

    private fun runIt(ad : Set<Int>) : List<Int> {

        var diffs = mutableListOf<Int>()
        var jolt = 0
        for(adapter in ad)
        {
                diffs.add(adapter - jolt)

            jolt = adapter
        }
        return diffs
    }

    private fun runIt2(ad : Set<Int>) : Boolean {
        var jolt = 0
        for(adapter in ad)
        {
            if( adapter-jolt < 4)
                jolt = adapter
            else
                return false

        }
        return true
    }

    override fun Result1(): String {
        var newAdapters = adapters.toMutableSet()
        newAdapters.add(newAdapters.max()?.plus(3))
        val diffs = runIt(newAdapters)
        return ( diffs.count ({ it == 1 })* diffs.count({it == 3})).toString()
    }

    private fun travers(ad : Set<Int>) : Long {
        var count = 1L
        for(i in 1 until ad.size-1) {
            var newset = ad.toMutableSet()
            newset.remove(newset.elementAt(i))
            if(runIt2(newset) && solutions.add(newset)) {
                count += travers(newset)
            }
        }
        return count
    }

    override fun Result2(): String {
        var result = 0

        var newAdapters = adapters.toMutableSet()
        newAdapters.add(0)
        newAdapters.add(newAdapters.max()?.plus(3))

        return travers(newAdapters.toSortedSet()).toString()
    }
}
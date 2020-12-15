package com.lewilewi.AoC2020

import java.io.InputStreamReader

public class Day15(input : String) : IAoC {
    val numbers = input.split(",").map{it.toInt()}.toMutableList()

    private fun runIt(until : Int) : Int
    {
        var numbersMap = mutableMapOf<Int,Int>()
        var curr = 0
        for(n in 0..numbers.lastIndex-1)
            numbersMap[numbers[n]] = n+1
        var last = numbers[numbers.lastIndex]
        for(i in numbers.size..until-1)
        {
            if(!numbersMap.containsKey(last))
                curr = 0
            else
                curr = i - numbersMap[last]!!

            numbersMap[last] = i
            last = curr
        }
        return curr
    }
    override fun Result1(): String {
        return runIt(2020).toString()
    }

    override fun Result2(): String {
        return runIt(30000000).toString()
    }
}
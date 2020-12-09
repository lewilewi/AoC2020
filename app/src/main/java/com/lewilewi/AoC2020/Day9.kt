package com.lewilewi.AoC2020

import java.io.InputStreamReader

public class Day9(input : String) : IAoC {
    val numbers = input.split("\n").map{it.toLong()}

    private fun verifyAt(index : Int) : Boolean {
        for( i in (index - 25) until index) {
            for (j in (index - 25) until index) {
                if (j != i && numbers[i] + numbers[j] == numbers[index])
                    return true;
            }
        }
        return false;
    }

    private fun findRangeThatSumsUpTo(number : Long) : Set<Long>{
        for( i in numbers.indices) {
            var sum = numbers[i]
            for (j in i+1 until numbers.size) {
                sum += numbers[j]
                if (sum == number)
                    return numbers.subList(i, j).toSortedSet()
            }
        }
        return setOf(0)
    }

    override fun Result1(): String {
        for(i in 25 until numbers.size) {
            if(!verifyAt(i))
                return numbers[i].toString()
        }
        return "zefix"
    }

    override fun Result2(): String {
        for(i in 25 until numbers.size) {
            if(!verifyAt(i))
            {
                var setOfNumbers = findRangeThatSumsUpTo(numbers[i])
                return (setOfNumbers.first() + setOfNumbers.last()).toString()
           }
        }
        return "zeftix"
    }
}
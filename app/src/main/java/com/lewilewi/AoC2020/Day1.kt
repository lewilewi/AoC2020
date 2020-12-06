package com.lewilewi.AoC2020

import java.io.InputStreamReader

public class Day1(input : String) : IAoC {
    val lines = input.split("\n")

    override fun Result1(): String {
        var result = 0
        var input = mutableListOf<Int>()
        lines.forEach {
            input.add(it.toInt())
        }

        for (i in input.indices) {
            for (j in input.indices) {
                if (i != j && input[i] + input[j] == 2020) {
                    result = input[i] * input[j]
                    break
                }
            }
            if (result != 0) {
                break
            }
        }
        return result.toString()
    }

    override fun Result2(): String {
        var result = 0
        var input = mutableListOf<Int>()
        lines.forEach {
            input.add(it.toInt())
        }

        for (i in input.indices) {
            for (j in input.indices) {
                for (k in input.indices) {
                    if (i != j && i != k && j != k && input[i] + input[j] + input[k] == 2020) {
                        result = input[i] * input[j] * input[k]
                        break
                    }
                }
                if (result != 0) {
                    break
                }
            }
            if (result != 0)
                break
        }
        return result.toString()
    }
}
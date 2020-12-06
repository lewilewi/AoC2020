package com.lewilewi.AoC2020

import android.util.Log
import java.io.InputStreamReader

public class Day6(input : String) : IAoC {
    var groups = mutableListOf<List<String>>()

    init {
        input.split("\n\n").map { groups.add(it.split("\n")) }
    }

    override fun Result1(): String {
        var result = 0
        for(group in groups) {
            var test = "".toSet()
            for (answers in group) {
                test = (test + answers.toSet()).toMutableSet()
            }
            result += test.size
        }
        return result.toString()
    }

    override fun Result2(): String {
        var result = 0
        var all = List<Char>(26) {'a' + it}.toSet()
        for(group in groups) {
            var test = all
            for (answers in group) {
                test = test.intersect(answers.toSet()).toMutableSet()
            }
            result += test.size
        }
        return result.toString()
    }
}
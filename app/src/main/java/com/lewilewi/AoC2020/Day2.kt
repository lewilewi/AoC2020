package com.lewilewi.AoC2020

import android.util.Log

public class Day2(lines: List<String>) : IAoC {
    var lines = lines

    override fun Result1(): String {
        var result = 0
        for (line in lines) {
            val tokens = line.split("-", ":", " ")
            val min = tokens[0].toInt()
            val max = tokens[1].toInt()
            val c = tokens[2].toCharArray()[0]
            val pass = tokens[4]

            val count = pass.count { i -> i == c }
            if (count >= min && count <= max) {
                result++
            }
        }
        return result.toString()
    }

    override fun Result2(): String {
        var result = 0
        for (line in lines) {
            val tokens = line.split("-", ":", " ")
            val pos1 = tokens[0].toInt() - 1
            val pos2 = tokens[1].toInt() - 1
            val c = tokens[2].toCharArray()[0]
            val pass = tokens[4]

            var firstOK = false
            var secOK = false
            if (pass.length > pos1 && pass[pos1] == c)
                firstOK = true

            if (pass.length > pos2 && pass[pos2] == c)
                secOK = true

            if (secOK && !firstOK || !secOK && firstOK)
                result++
        }
        return result.toString()
    }
}
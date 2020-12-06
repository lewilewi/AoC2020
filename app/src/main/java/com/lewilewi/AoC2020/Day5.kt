package com.lewilewi.AoC2020

import java.io.InputStreamReader

public class Day5(input : String) : IAoC {
    val lines = input.split("\n")
    var ids = mutableListOf<Int>()

    override fun Result1(): String {
        var result = 0

        for (pass in lines) {
            val numberOfRows = 128
            val numberOfCols = 8

            var currentRowMin = 0
            var currentRowMax = numberOfRows
            var currentColMin = 0
            var currentColMax = numberOfCols

            for (ch in pass) {
                if (ch == 'B') {
                    currentRowMin = currentRowMin + (currentRowMax - currentRowMin) / 2
                } else if (ch == 'F') {
                    currentRowMax = currentRowMax - (currentRowMax - currentRowMin) / 2
                } else if (ch == 'L') {
                    currentColMax = currentColMax - (currentColMax - currentColMin) / 2
                } else if (ch == 'R') {
                    currentColMin = currentColMin + (currentColMax - currentColMin) / 2
                }
            }
            val id = currentRowMin * 8 + currentColMin
            ids.add(id)
            if (id > result)
                result = id
        }
        return result.toString()
    }

    override fun Result2(): String {
        var result = 0

        var idsMissing = mutableListOf<Int>()
        for (row in 0..127) {
            for (col in 0..7) {
                var id = row * 8 + col
                if (!ids.contains(id)) {
                    idsMissing.add(id)
                }
            }
        }

        for (id in idsMissing) {
            if (ids.contains(id - 1) && ids.contains(id + 1))
                result = id
        }

        return result.toString()
    }
}
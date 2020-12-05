package com.lewilewi.AoC2020

import android.util.Log

public class Day3(lines: List<String>) : IAoC {
    var lines = lines

    fun CalcSlope(stepCol: Int, stepRow: Int): Long {
        var result = 0L

        var col = 0
        var row = 0
        var maxColIndex = lines[0].length - 1
        var maxRowIndex = lines.size - 1

        while (true) {
            if (lines[row][col] == '#')
                result++

            col += stepCol
            if (col > maxColIndex) {
                col = col - maxColIndex - 1
            }
            row += stepRow
            if (row > maxRowIndex)
                break
        }

        return result
    }

    override fun Result1(): String {
        var result = 0L

        result = CalcSlope(3, 1)


        return result.toString()
    }

    override fun Result2(): String {
        var result = 0L

        result = CalcSlope(3, 1) *
                CalcSlope(1, 1) *
                CalcSlope(5, 1) *
                CalcSlope(7, 1) *
                CalcSlope(1, 2)

        return result.toString()
    }
}
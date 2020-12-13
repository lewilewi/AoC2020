package com.lewilewi.AoC2020

import android.util.Log
import java.lang.ClassCastException
import java.lang.Integer.max
import java.lang.Math.min

public class Day11(input: String) : IAoC {
    var seats = input.split("\n").map { it.toCharArray() }.toTypedArray()
    var seats3 = input.split("\n").map { it.toCharArray() }.toTypedArray()

    private fun countNeighbors(arr: Array<CharArray>, row: Int, col: Int): Int {
        var count = 0
        for (i in row - 1..row + 1)
            for (j in col - 1..col + 1)
                if (!(i == row && j == col))
                    if (j >= 0 && i >= 0 && i < arr.size && j < arr[i].size && arr[i][j] == '#')
                        count++
        return count
    }

    private fun countNeighbors2(arr: Array<CharArray>, row: Int, col: Int): Int {
        // there might be a smarter way
        // or at least potencial for refactoring here to eliminate duplicated code in while loop
        var count = 0
        var i = row
        var j = col
        while (++j < arr[i].size) {
            if (arr[i][j] == '#') {
                count++
                break
            } else if (arr[i][j] == 'L')
                break;
        }

        i = row
        j = col
        while (--j >= 0) {
            if (arr[i][j] == '#') {
                count++
                break
            } else if (arr[i][j] == 'L')
                break;
        }

        i = row
        j = col
        while (++i < arr.size) {
            if (arr[i][j] == '#') {
                count++
                break
            } else if (arr[i][j] == 'L')
                break;
        }

        i = row
        j = col
        while (--i >= 0) {
            if (arr[i][j] == '#') {
                count++
                break
            } else if (arr[i][j] == 'L')
                break;
        }

        i = row
        j = col
        while (++i < arr.size && ++j < arr[i].size) {
            if (arr[i][j] == '#') {
                count++
                break
            } else if (arr[i][j] == 'L')
                break;
        }

        i = row
        j = col
        while (--i >= 0 && --j >= 0) {
            if (arr[i][j] == '#') {
                count++
                break
            } else if (arr[i][j] == 'L')
                break;
        }

        i = row
        j = col
        while (--i >= 0 && ++j < arr[i].size) {
            if (arr[i][j] == '#') {
                count++
                break
            } else if (arr[i][j] == 'L')
                break;
        }

        i = row
        j = col
        while (++i < arr.size && --j >= 0) {
            if (arr[i][j] == '#') {
                count++
                break
            } else if (arr[i][j] == 'L')
                break;
        }
        return count
    }

    override fun Result1(): String {
        var changes = 1
        while (changes > 0) {
            changes = 0
            var seats2 = seats.copyOf().map { it.copyOf() }.toTypedArray()
            for (i in seats.indices) for (j in seats[i].indices) {
                val count = countNeighbors(seats, i, j)
                if (seats[i][j] == 'L' && count == 0) {
                    seats2[i][j] = '#'
                    changes++
                } else if (seats[i][j] == '#' && count >= 4) {
                    seats2[i][j] = 'L'
                    changes++
                } else {
                    seats2[i][j] = seats[i][j]
                }
            }
            seats = seats2
        }

        var count = 0
        for (i in seats.indices) for (j in seats[i].indices) {
            if (seats[i][j] == '#')
                count++
        }
        return count.toString()
    }

    override fun Result2(): String {
        seats = seats3
        var changes = 1
        while (changes > 0) {
            changes = 0
            var seats2 = seats.copyOf().map { it.copyOf() }.toTypedArray()
            for (i in seats.indices) for (j in seats[i].indices) {
                val count = countNeighbors2(seats, i, j)
                if (seats[i][j] == 'L' && count == 0) {
                    seats2[i][j] = '#'
                    changes++
                } else if (seats[i][j] == '#' && count >= 5) {
                    seats2[i][j] = 'L'
                    changes++
                } else {
                    seats2[i][j] = seats[i][j]
                }
            }
            seats = seats2
        }

        var count = 0
        for (i in seats.indices) for (j in seats[i].indices) {
            if (seats[i][j] == '#')
                count++
        }
        return count.toString()
    }
}
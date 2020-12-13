package com.lewilewi.AoC2020

import java.io.InputStreamReader
import java.lang.Math.abs

public class Day12(input: String) : IAoC {
    private val lines = input.split("\n")

    override fun Result1(): String {
        var direction = 90
        var x = 0
        var y = 0
        for (line in lines) {
            val action = line[0]
            val value = line.substring(1).toInt()
            if (action == 'F') {
                if (direction == 0)
                    y += value
                else if (direction == 90)
                    x += value
                else if (direction == 180)
                    y -= value
                else if (direction == 270)
                    x -= value
            } else if (action == 'N') {
                y += value
            } else if (action == 'S') {
                y -= value
            } else if (action == 'E') {
                x += value
            } else if (action == 'W') {
                x -= value
            } else if (action == 'L') {
                direction -= value
                if (direction < 0)
                    direction += 360
            } else if (action == 'R') {
                direction += value
                if (direction >= 360)
                    direction -= 360
            }
        }
        return (abs(x) + abs(y)).toString()
    }

    override fun Result2(): String {
        var x = 0
        var y = 0
        var wx = 10
        var wy = 1
        for (line in lines) {
            var action = line[0]
            var value = line.substring(1).toInt()
            if (action == 'F') {
                x += wx * value
                y += wy * value
            } else if (action == 'N') {
                wy += value
            } else if (action == 'S') {
                wy -= value
            } else if (action == 'E') {
                wx += value
            } else if (action == 'W') {
                wx -= value
            } else if (action == 'L') {
                value = -value + 360
                action = 'R'
            }
            if (action == 'R') {
                if (value == 90) {
                    var temp = wx
                    wx = wy
                    wy = temp * -1
                } else if (value == 180) {
                    wx *= -1
                    wy *= -1
                } else if (value == 270) {
                    var temp = wx
                    wx = wy * -1
                    wy = temp
                }
            }
        }
        return (abs(x) + abs(y)).toString()
    }
}
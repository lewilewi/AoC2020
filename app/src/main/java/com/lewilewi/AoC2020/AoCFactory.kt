package com.lewilewi.AoC2020

import android.content.Context
import android.util.Log
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.reflect.Constructor

public class AoCFactory {

    fun CreateAoC(day: Int, c: Context): IAoC {
        var name = "Day$day"
        val reader = InputStreamReader(c.assets.open(name))
        val lines = reader.readLines()
        var cl = Class.forName("com.lewilewi.AoC2020." + name)
        return cl.getConstructor(List::class.java).newInstance(lines) as IAoC
    }
}
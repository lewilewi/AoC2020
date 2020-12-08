package com.lewilewi.AoC2020

import java.io.InputStreamReader
import java.lang.Exception

public class Day8(input: String) : IAoC {

    lateinit var inputProgram: Array<Array<String>>

    init {
        val instructions = input.split("\n")
        inputProgram = Array<Array<String>>(instructions.size) { arrayOf("", "") }
        for (i in instructions.indices)
            inputProgram[i] = instructions[i].split(" ").toTypedArray()
    }

    fun getNextInstruction(index: Int): Int {
        if (inputProgram[index][0] == "jmp")
            return index + inputProgram[index][1].toInt()
        else
            return index + 1
    }

    fun runIt(toTheEnd: Boolean): Int {
        var loopDetection = mutableSetOf<Int>()
        var programCount = 0;
        var instruction = 0
        while (instruction < inputProgram.size && loopDetection.add(instruction)) {
            if (inputProgram[instruction][0] == "acc")
                programCount += inputProgram[instruction][1].toInt()
            instruction = getNextInstruction(instruction)
        }

        if (instruction >= inputProgram.size || !toTheEnd)
            return programCount
        else
            return -1
    }

    override fun Result1(): String {
        return runIt(false).toString()
    }

    override fun Result2(): String {
        for (instruction in inputProgram) {
            if (instruction[0] == "jmp")
                instruction[0] = "nop"
            else if (instruction[0] == "nop")
                instruction[0] = "jmp"

            // run the program
            val ret = runIt(true)

            // change back
            if (instruction[0] == "jmp")
                instruction[0] = "nop"
            else if (instruction[0] == "nop")
                instruction[0] = "jmp"

            if (ret != -1)
                return ret.toString()
        }
        return "zefix"
    }
}
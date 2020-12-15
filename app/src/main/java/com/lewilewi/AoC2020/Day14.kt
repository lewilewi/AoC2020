package com.lewilewi.AoC2020

import android.util.Log
import java.io.InputStreamReader
import java.util.*

public class Day14(input: String) : IAoC {
    val lines = input.split("\n")

    override fun Result1(): String {
        var mem = mutableMapOf<Long, Long>()
        var mask = 0L
        var maskValue = 0L
        for (line in lines) {
            if (line.startsWith("mask = ")) {
                mask = line.replace("mask = ", "").replace("0", "1").replace("X", "0").toLong(2)
                maskValue = line.replace("mask = ", "").replace("X", "0").toLong(2)
            } else {
                var tokens = line.split("[", "]", " = ")
                var address = tokens[1].toLong()
                var value = tokens[3].toLong()
                mem[address] = (value and mask.inv()) or maskValue
            }
        }
        return mem.values.sum().toString()
    }

    override fun Result2(): String {
        var mem = mutableMapOf<Long, Long>()
        var mask = 0L
        var maskValue = 0L
        var maskString = ""
        var Xaddresses = mutableListOf<Long>()
        for (line in lines) {
            if (line.startsWith("mask = ")) {
                Xaddresses.clear()
                mask = line.replace("mask = ", "").replace("1", "0").replace("X", "1").toLong(2)
                maskValue = line.replace("mask = ", "").replace("X", "0").toLong(2)
                maskString = line.replace("mask = ", "")

                // find and all permutations of addresses containing X
                for (i in 0..maskString.lastIndex) {
                    if (maskString[maskString.lastIndex - i] == 'X') {
                        // duplicate addresses found so far and set bit for new part
                        var addressesNew = Xaddresses.toMutableList()
                        if (addressesNew.isEmpty()) {
                            addressesNew.add(1L.shl(i))
                            addressesNew.add(0)
                        }

                        for (ad in Xaddresses)
                            addressesNew.add(ad or 1L.shl(i))

                        Xaddresses = addressesNew
                    }
                }
            } else {
                var tokens = line.split("[", "]", " = ")
                var address = tokens[1].toLong()
                var value = tokens[3].toLong()

                // apply mask without Xs
                address = (address and mask.inv()) or maskValue

                // write to all Xs addresses
                for (ad in Xaddresses) {
                    mem[address or ad] = value
                }
            }
        }
        return mem.values.sum().toString()
    }
}
package com.lewilewi.AoC2020

import java.io.InputStreamReader

public class Day7(input: String) : IAoC {
    val rulesString = input.split("\n")
    val rules = mutableMapOf<String, List<String>>()

    init {
        for (rule in rulesString) {
            var tokens = rule.split(" bags contain ")
            var key = tokens[0]
            var childen = tokens[1].split(" ").map { it.trim() }.chunked(4)
            var elements = mutableListOf<String>()
            for (value in childen) {
                if (value.size == 4) {
                    var amount = value[0].toInt()
                    for (i in 1..amount)
                        elements.add(value[1] + " " + value[2])
                }
            }
            rules[key] = elements
        }
    }

    fun traverse1(name: String): List<String> {
        var ret = mutableListOf<String>()
        for (rule in rules) {
            if (rule.value.contains(name)) {
                ret.add(rule.key)
                for (list in traverse1(rule.key))
                    ret.add(list)
            }
        }
        return ret
    }

    fun traverse2(name: String): List<String> {
        var ret = mutableListOf<String>()
        for (v in rules[name]!!) {
            ret.add(v)
            ret.addAll(traverse2(v))
        }
        return ret
    }

    override fun Result1(): String {
        return traverse1("shiny gold").toSet().count().toString()
    }

    override fun Result2(): String {
        return traverse2("shiny gold").count().toString()
    }
}
package com.lewilewi.AoC2020

public class Day4(lines: List<String>) : IAoC {
    var lines = lines

    override fun Result1(): String {
        var result = 0L
        val requiredFields = arrayOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        val all = lines.toString()
        val passwords = all.split(", , ")

        for (pass in passwords) {
            var valid = true
            for (req in requiredFields) {
                if (!pass.contains(req)) {

                    valid = false;
                    break;
                }
            }
            if (valid) {
                result++
            }
        }
        return result.toString()
    }

    override fun Result2(): String {
        var result = 0L
        val requiredFields = arrayOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        var all = lines.toString()
        all = all.removeSuffix("]")

        val passwords = all.split(", , ")

        for (pass in passwords) {
            var tokens = pass.split(',', ' ');
            var valid = true
            for (req in requiredFields) {
                if (!pass.contains(req)) {

                    valid = false;
                    break;
                }
            }
            if (valid) {
                var valid2 = true
                for (token in tokens) {
                    if (token == "")
                        continue

                    if (token.contains("byr")) {
                        var value = token.split(":")[1].toInt()
                        if (!(value >= 1920 && value <= 2002)) {
                            valid2 = false;
                            break;
                        }
                    }

                    if (token.contains("iyr")) {
                        var value = token.split(":")[1].toInt()
                        if (!(value >= 2010 && value <= 2020)) {
                            valid2 = false;
                            break;
                        }
                    }

                    if (token.contains("eyr")) {
                        var value = token.split(":")[1].toInt()
                        if (!(value >= 2020 && value <= 2030)) {
                            valid2 = false;
                            break;
                        }
                    }

                    if (token.contains("hgt")) {
                        var value = token.split(":")[1]
                        var isCM = value.endsWith("cm")
                        if (isCM)
                            value = value.replace("cm", "");
                        else
                            value = value.replace("in", "");

                        var value2 = value.toInt()

                        if (isCM) {
                            if (!(value2 >= 150 && value2 <= 193)) {
                                valid2 = false;
                                break;
                            }
                        } else {
                            if (!(value2 >= 59 && value2 <= 76)) {
                                valid2 = false;
                                break;
                            }
                        }
                    }

                    if (token.contains("hcl")) {
                        var value = token.split(":")[1]
                        if (!("[#0-9a-f]{7}".toRegex().matches(value))) {

                            valid2 = false
                            break
                        }
                    }

                    if (token.contains("ecl")) {
                        var value = token.split(":")[1]
                        val colors = arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                        if (!colors.contains(value)) {
                            valid2 = false
                            break
                        }
                    }

                    if (token.contains("pid")) {
                        var value = token.split(":")[1]
                        if (!("[0-9]{9}".toRegex().matches(value))) {

                            valid2 = false
                            break
                        }
                    }
                }
                if (valid2)
                    result++
            }
        }
        return result.toString()
    }
}
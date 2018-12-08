package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day01 : Day(1) {
    private val input = getInputAsList().map { it.toInt() }

    override fun part1(): String {
        return input.fold(0) { acc, v ->
            acc + v
        }.toString()
    }

    override fun part2(): String {
        var value = 0
        var found = false
        val foundValues = HashSet<Int>()

        do {
            input.forEach {
                if (!found) {
                    value += it
                    found = !foundValues.add(value)
                }
            }
        } while (!found)

        return value.toString()
    }
}

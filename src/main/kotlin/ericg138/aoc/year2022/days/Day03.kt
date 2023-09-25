package ericg138.aoc.year2022.days

import ericg138.aoc.Day

class Day03 : Day(3) {
    private val input = getInputAsList()

    override fun part1(): String {
        var result = 0

        input.forEach {
            val first = it.substring(0, it.length / 2).toSet()
            val second = it.substring(it.length / 2).toSet()

            val intersect = first.intersect(second).first()
            result += getPriority(intersect)
        }

        return result.toString()
    }

    override fun part2(): String {
        var result = 0

        for (i in input.indices step 3) {
            val first = input[i].toSet()
            val second = input[i + 1].toSet()
            val third = input[i + 2].toSet()

            val intersect = first.intersect(second).intersect(third).first()
            result += getPriority(intersect)
        }

        return result.toString()
    }

    private fun getPriority(intersect: Char) = if (intersect.isUpperCase()) {
        intersect.code - 64 + 26
    } else {
        intersect.code - 96
    }
}

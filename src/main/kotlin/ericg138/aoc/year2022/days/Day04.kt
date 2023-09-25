package ericg138.aoc.year2022.days

import ericg138.aoc.Day

class Day04 : Day(4) {
    private val input = getInputAsList()

    override fun part1(): String {
        var result = 0

        input.forEach {
            val first = it.split(",")[0]
            val second = it.split(",")[1]
            val firstFrom = first.split("-")[0].toInt()
            val secondFrom = second.split("-")[0].toInt()
            val firstTo = first.split("-")[1].toInt()
            val secondTo = second.split("-")[1].toInt()

            if (firstFrom <= secondFrom && firstTo >= secondTo || firstFrom >= secondFrom && firstTo <= secondTo) {
                result++
            }
        }

        return result.toString()
    }

    override fun part2(): String {
        var result = 0

        input.forEach {
            val first = it.split(",")[0]
            val second = it.split(",")[1]
            val firstFrom = first.split("-")[0].toInt()
            val secondFrom = second.split("-")[0].toInt()
            val firstTo = first.split("-")[1].toInt()
            val secondTo = second.split("-")[1].toInt()

            if (!(firstTo < secondFrom || secondTo < firstFrom)) {
                result++
            }
        }

        return result.toString()
    }
}

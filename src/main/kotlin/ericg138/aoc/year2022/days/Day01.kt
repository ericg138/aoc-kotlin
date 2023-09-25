package ericg138.aoc.year2022.days

import ericg138.aoc.Day

class Day01 : Day(1) {
    private val input = getInputAsList()

    override fun part1(): String {
        var sum = 0
        var max = 0
        input.forEach {
            if (it.isEmpty()) {
                if (sum > max) {
                    max = sum
                }
                sum = 0
            } else {
                sum += it.toInt()
            }
        }
        return max.toString()
    }

    override fun part2(): String {
        var sum = 0
        val top3 = mutableListOf(0, 0, 0)
        input.forEach {
            if (it.isEmpty()) {
                if (sum > top3[0]) {
                    top3[0] = sum
                    top3.sort()
                }
                sum = 0
            } else {
                sum += it.toInt()
            }
        }
        return top3.sum().toString()
    }
}

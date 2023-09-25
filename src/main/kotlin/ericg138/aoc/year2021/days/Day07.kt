package ericg138.aoc.year2021.days

import ericg138.aoc.Day
import kotlin.math.abs

class Day07 : Day(7) {
    private val input = getInputAsString().split(",").map { Integer.parseInt(it) }

    override fun part1(): String {
        val map = HashMap<Int, Int>()

        for (i in input.minOf { it }..input.maxOf { it }) {
            var fuel = 0
            input.forEach {
                fuel += abs(it - i)
            }
            map[i] = fuel
        }

        return map.minOf { it.value }.toString()
    }

    override fun part2(): String {
        val map = HashMap<Int, Int>()

        for (i in input.minOf { it }..input.maxOf { it }) {
            var fuel = 0
            input.forEach {
                val fuelCurrent = abs(it - i)
                val offset = IntRange(0, fuelCurrent - 1).sum()
                fuel += fuelCurrent + offset
            }
            map[i] = fuel
        }

        return map.minOf { it.value }.toString()
    }
}

package ericg138.aoc.year2022.days

import ericg138.aoc.Day

class Day10 : Day(10) {
    private val input = getInputAsList()

    override fun part1(): String {
        var cycle = 0
        var value = 1
        var index = 0
        var waiting = false
        val values = mutableListOf<Int>()

        while (index < input.size) {
            cycle++
            if ((cycle - 20) % 40 == 0) {
                values.add(value * cycle)
            }

            val instruction = input[index]

            if (waiting) {
                waiting = false
                value += instruction.substring(5).toInt()
                index++
            } else if (instruction.startsWith("addx")) {
                waiting = true
            } else {
                index++
            }
        }

        return values.sum().toString()
    }

    override fun part2(): String {
        var cycle = 0
        var value = 1
        var index = 0
        var waiting = false
        val lines = (0..5).map { (0..39).map { '.' }.toMutableList() }.toMutableList()

        while (index < input.size) {
            for (i in value - 1..value + 1) {
                if (i == cycle % 40) {
                    lines[cycle / 40][i] = '#'
                }
            }
            cycle++

            val instruction = input[index]

            if (waiting) {
                waiting = false
                value += instruction.substring(5).toInt()
                index++
            } else if (instruction.startsWith("addx")) {
                waiting = true
            } else {
                index++
            }
        }

        return "\n" + lines.joinToString("\n") { it.joinToString("") }
    }
}

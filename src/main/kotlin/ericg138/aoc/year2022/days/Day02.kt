package ericg138.aoc.year2022.days

import ericg138.aoc.Day

class Day02 : Day(2) {
    private val input = getInputAsList()

    override fun part1(): String {
        var score = 0

        input.forEach {
            val opponent = it[0]
            val you = it[2]

            if (opponent == 'A') {
                score += when (you) {
                    'X' -> 1 + 3
                    'Y' -> 2 + 6
                    'Z' -> 3 + 0
                    else -> 0
                }
            }

            if (opponent == 'B') {
                score += when (you) {
                    'X' -> 1 + 0
                    'Y' -> 2 + 3
                    'Z' -> 3 + 6
                    else -> 0
                }
            }

            if (opponent == 'C') {
                score += when (you) {
                    'X' -> 1 + 6
                    'Y' -> 2 + 0
                    'Z' -> 3 + 3
                    else -> 0
                }
            }
        }

        return score.toString()
    }

    override fun part2(): String {
        var score = 0

        input.forEach {
            val opponent = it[0]
            val you = it[2]

            if (opponent == 'A') {
                score += when (you) {
                    'X' -> 3 + 0
                    'Y' -> 1 + 3
                    'Z' -> 2 + 6
                    else -> 0
                }
            }

            if (opponent == 'B') {
                score += when (you) {
                    'X' -> 1 + 0
                    'Y' -> 2 + 3
                    'Z' -> 3 + 6
                    else -> 0
                }
            }

            if (opponent == 'C') {
                score += when (you) {
                    'X' -> 2 + 0
                    'Y' -> 3 + 3
                    'Z' -> 1 + 6
                    else -> 0
                }
            }
        }

        return score.toString()
    }
}

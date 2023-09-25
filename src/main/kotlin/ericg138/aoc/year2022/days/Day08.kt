package ericg138.aoc.year2022.days

import ericg138.aoc.Day
import ericg138.aoc.util.Coordinate

class Day08 : Day(8) {
    private val input = getInputAsList().map { line -> line.toCharArray().map { it.digitToInt() } }

    override fun part1(): String {
        val visibleTrees = mutableSetOf<Coordinate>()

        // From top
        for (x in input.first().indices) {
            var height = -1
            for (y in input.indices) {
                if (input[y][x] > height) {
                    height = input[y][x]
                    visibleTrees.add(Coordinate(x, y))
                }
            }
        }

        // From bottom
        for (x in input.first().indices) {
            var height = -1
            for (y in input.size - 1 downTo 0) {
                if (input[y][x] > height) {
                    height = input[y][x]
                    visibleTrees.add(Coordinate(x, y))
                }
            }
        }

        // From left
        for (y in input.indices) {
            var height = -1
            for (x in input.first().indices) {
                if (input[y][x] > height) {
                    height = input[y][x]
                    visibleTrees.add(Coordinate(x, y))
                }
            }
        }

        // From right
        for (y in input.indices) {
            var height = -1
            for (x in input.first().size - 1 downTo 0) {
                if (input[y][x] > height) {
                    height = input[y][x]
                    visibleTrees.add(Coordinate(x, y))
                }
            }
        }

        return visibleTrees.size.toString()
    }

    override fun part2(): String {
        var highScore = 0

        for (x in 0 until input.first().size) {
            for (y in input.indices) {
                val height = input[y][x]

                // Right
                var right = 0
                for (x2 in x + 1 until input.first().size) {
                    right++
                    if (input[y][x2] >= height) {
                        break
                    }
                }

                // Left
                var left = 0
                for (x2 in x - 1 downTo 0) {
                    left++
                    if (input[y][x2] >= height) {
                        break
                    }
                }

                // Down
                var down = 0
                for (y2 in y + 1 until input.size) {
                    down++
                    if (input[y2][x] >= height) {
                        break
                    }
                }

                // Up
                var up = 0
                for (y2 in y - 1 downTo 0) {
                    up++
                    if (input[y2][x] >= height) {
                        break
                    }
                }

                val score = right * left * down * up
                if (score > highScore) {
                    highScore = score
                }
            }
        }

        return highScore.toString()
    }
}

package ericg138.aoc.year2020.days

import ericg138.aoc.Day
import ericg138.aoc.util.Coordinate

class Day03 : Day(3) {
    private val input = getInputAsList()
    private val width = input[0].length
    private val height = input.size
    private val trees = mutableListOf<Coordinate>()

    init {
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, square -> if (square == '#') trees.add(Coordinate(x, y)) }
        }
    }

    override fun part1(): String {
        return takeSlope(3, 1).toString()
    }

    override fun part2(): String {
        return (takeSlope(1, 1) * takeSlope(3, 1) * takeSlope(5, 1) * takeSlope(7, 1) * takeSlope(1, 2)).toString()
    }

    private fun takeSlope(slopeX: Int, slopeY: Int): Long {
        var treesFound = 0L

        val current = Coordinate(0, 0)
        while (current.y < height) {
            current.offsetX(slopeX)
            current.offsetY(slopeY)

            if (current.x >= width) {
                current.x = current.x - width
            }

            if (trees.contains(current)) {
                treesFound++
            }
        }

        return treesFound
    }
}

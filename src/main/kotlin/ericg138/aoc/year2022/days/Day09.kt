package ericg138.aoc.year2022.days

import ericg138.aoc.Day
import ericg138.aoc.util.Coordinate
import kotlin.math.abs

class Day09 : Day(9) {
    private val input = getInputAsList()

    override fun part1(): String {
        val visited = mutableSetOf<Coordinate>()
        val coordHead = Coordinate(0, 0)
        val coordTail = Coordinate(0, 0)

        input.forEach {
            val direction = it[0]
            val steps = it.substring(2).toInt()

            for (i in 0 until steps) {
                when (direction) {
                    'R' -> coordHead.offsetX(1)
                    'L' -> coordHead.offsetX(-1)
                    'U' -> coordHead.offsetY(1)
                    'D' -> coordHead.offsetY(-1)
                }

                moveKnot(coordTail, coordHead)

                visited.add(Coordinate(coordTail.x, coordTail.y))
            }
        }

        return visited.size.toString()
    }

    override fun part2(): String {
        val visited = mutableSetOf<Coordinate>()
        val coordHead = Coordinate(0, 0)
        val knots = (1..9).map { Coordinate(0, 0) }

        input.forEach {
            val direction = it[0]
            val steps = it.substring(2).toInt()

            for (i in 0 until steps) {
                when (direction) {
                    'R' -> coordHead.offsetX(1)
                    'L' -> coordHead.offsetX(-1)
                    'U' -> coordHead.offsetY(1)
                    'D' -> coordHead.offsetY(-1)
                }

                knots.forEachIndexed { index, knot -> moveKnot(knot, if (index == 0) coordHead else knots[index - 1]) }

                visited.add(Coordinate(knots.last().x, knots.last().y))
            }
        }

        return visited.size.toString()
    }

    private fun moveKnot(knot: Coordinate, previous: Coordinate) {
        if (abs(previous.x - knot.x) > 1) {
            knot.offsetX(if (previous.x > knot.x) 1 else -1)
            if (knot.y != previous.y) {
                knot.offsetY(if (previous.y > knot.y) 1 else -1)
            }
        }

        if (abs(previous.y - knot.y) > 1) {
            knot.offsetY(if (previous.y > knot.y) 1 else -1)
            if (knot.x != previous.x) {
                knot.offsetX(if (previous.x > knot.x) 1 else -1)
            }
        }
    }
}

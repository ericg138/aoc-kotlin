package ericg138.aoc.year2022.days

import ericg138.aoc.Day

class Day06 : Day(6) {
    private val input = getInputAsString()

    override fun part1(): String {
        return findMarkerIndex(4).toString()
    }

    override fun part2(): String {
        return findMarkerIndex(14).toString()
    }

    private fun findMarkerIndex(size: Int): Int {
        val stack = ArrayDeque<Char>()
        input.forEachIndexed { index, c ->
            stack.add(c)
            if (stack.size == size) {
                if (HashSet(stack).size == size) {
                    return index + 1
                }
                stack.removeFirst()
            }
        }
        return -1
    }
}

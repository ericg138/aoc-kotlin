package ericg138.aoc.year2021.days

import ericg138.aoc.Day
import ericg138.aoc.util.Coordinate
import java.util.*

class Day15 : Day(15) {
    private val input = getInputAsList()

    override fun part1(): String {
        return findLowestRisk(input)
    }

    override fun part2(): String {
        val cave = (0 until input.size * 5).map { "" }.toMutableList()

        for (y in 0 until 5) {
            for (x in 0 until 5) {
                input.forEachIndexed { index, line ->
                    val caveIndex = index + y * input.size
                    var newLine = cave[caveIndex]

                    line.forEach {
                        val risk = it.toString().toInt() + y + x
                        newLine += if (risk >= 10) risk - 9 else risk
                    }

                    cave[caveIndex] = newLine
                }
            }
        }

        return findLowestRisk(cave)
    }

    /**
     * Dijkstra algorithm implementation
     */
    private fun findLowestRisk(cave: List<String>): String {
        // Use binary heap (priority queue) to store the evaluated nodes
        val heap = PriorityQueue<Pair<Coordinate, Int>>(compareBy<Pair<Coordinate, Int>?> { it!!.second })
        val evaluated = mutableSetOf<Coordinate>()

        var current = Pair(Coordinate(0, 0), 0)
        val exit = Coordinate(cave[0].length - 1, cave.size - 1)

        while (current.first != exit) {
            val coord = current.first

            // Find all possible neighbors
            val neighbors = mutableListOf<Coordinate>()
            if (coord.x < cave[0].length - 1) {
                neighbors.add(Coordinate(coord.x + 1, coord.y))
            }
            if (coord.x > 0) {
                neighbors.add(Coordinate(coord.x - 1, coord.y))
            }
            if (coord.y < cave.size - 1) {
                neighbors.add(Coordinate(coord.x, coord.y + 1))
            }
            if (coord.y > 0) {
                neighbors.add(Coordinate(coord.x, coord.y - 1))
            }

            // Evaluate each neighbor not yet evaluated
            neighbors.filterNot { it in evaluated }.forEach { neighbor ->
                val neighborValue = cave[neighbor.y][neighbor.x].toString().toInt()
                heap.add(Pair(neighbor, current.second + neighborValue))
                evaluated.add(neighbor)
            }

            // Get node with lowest risk
            current = heap.remove()
        }

        return current.second.toString()
    }
}

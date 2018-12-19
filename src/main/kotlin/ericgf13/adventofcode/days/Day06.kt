package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day
import kotlin.math.abs

class Day06 : Day(6) {

    data class Coord(val x: Int, val y: Int)

    private val coords = getInputAsList().map { Coord(it.split(", ")[0].toInt(), it.split(", ")[1].toInt()) }
    private val xMax = coords.maxBy { it.x }!!.x
    private val yMax = coords.maxBy { it.y }!!.y

    override fun part1(): String {
        val map = HashMap<Coord, MutableSet<Coord>>()

        for (i in 0..xMax) {
            for (j in 0..yMax) {
                val currentCoord = Coord(i, j)
                var minDistance = -1
                var multiple = false
                lateinit var closestCoord: Coord

                for (coord in coords) {
                    val distance = manhattanDistance(currentCoord, coord)
                    if (minDistance == -1 || distance < minDistance) {
                        minDistance = distance
                        closestCoord = coord
                        multiple = false
                    } else if (distance == minDistance) {
                        multiple = true
                    }
                }

                if (!multiple) {
                    map.getOrPut(closestCoord) { mutableSetOf() }.add(currentCoord)
                }
            }
        }

        return map.values.filter { coords ->
            coords.none { it.x == 0 || it.y == 0 || it.x == xMax || it.y == yMax }
        }.maxBy { it.size }!!.size.toString()
    }

    override fun part2(): String {
        var areaSize = 0

        for (i in 0..xMax) {
            for (j in 0..yMax) {
                val currentCoord = Coord(i, j)
                var totalDistance = 0

                for (coord in coords) {
                    totalDistance += manhattanDistance(currentCoord, coord)
                    if (totalDistance > 10000) {
                        break
                    }
                }

                if (totalDistance < 10000) {
                    areaSize++
                }
            }
        }

        return areaSize.toString()
    }

    private fun manhattanDistance(coordA: Coord, coordB: Coord): Int {
        return abs(coordA.x - coordB.x) + abs(coordA.y - coordB.y)
    }
}

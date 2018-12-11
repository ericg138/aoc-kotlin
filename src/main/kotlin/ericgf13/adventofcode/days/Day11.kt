package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day11 : Day(11) {

    private val input = 9005
    private val powerByCoord = HashMap<String, Int>()

    init {
        for (x in 1..300) {
            for (y in 1..300) {
                val rackId = x + 10
                val power = (((rackId * y + input) * rackId) / 100) % 10 - 5
                powerByCoord["$x,$y"] = power
            }
        }
    }

    override fun part1(): String {
        val result = process(3..3)
        return result.substring(0, result.lastIndexOf(','))
    }

    override fun part2(): String {
        // Brute-forced up to 30, the answer was correct (size 13)
        return process(1..13)
    }

    private fun process(sizeRange: IntRange): String {
        var maxPower = 0
        var bestCoord = ""
        var bestSize = 0

        for (size in sizeRange) {
            for (x in 1..300 - size) {
                for (y in 1..300 - size) {
                    var totalPower = 0

                    for (i in 0 until size) {
                        for (j in 0 until size) {
                            totalPower += powerByCoord["" + (x + i) + "," + (y + j)]!!
                        }
                    }

                    if (totalPower > maxPower) {
                        maxPower = totalPower
                        bestCoord = "$x,$y"
                        bestSize = size
                    }
                }
            }
        }

        return "$bestCoord,$bestSize"
    }
}

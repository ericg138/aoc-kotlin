package ericg138.aoc.year2018.days

import ericg138.aoc.Day

class Day02 : Day(2) {
    private val input = getInputAsList()

    override fun part1(): String {
        var count2 = 0
        var count3 = 0

        input.forEach { id ->
            var has2 = false
            var has3 = false

            id.forEach { letter ->
                val length = id.replace(Regex("[^$letter]"), "").length
                if (!has3 && length == 3) {
                    count3++
                    has3 = true
                } else if (!has2 && length == 2) {
                    count2++
                    has2 = true
                }
            }
        }

        return (count2 * count3).toString()
    }

    override fun part2(): String {
        var previous = ""

        input.sorted().forEach { id ->
            var diffCount = 0
            var diffIndex = 0

            for (i in previous.indices) {
                if (previous[i] != id[i]) {
                    diffIndex = i
                    if (++diffCount > 1) {
                        break
                    }
                }
            }

            if (diffCount == 1) {
                return id.substring(0, diffIndex) + id.substring(diffIndex + 1)
            }

            previous = id
        }

        throw Exception("no result found")
    }
}

package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day18 : Day(18) {

    private var part1Total = 0
    private var part2Total = 0

    override fun part1(): String {
        var map = HashMap<String, Char>()

        getInputAsList().forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                map["$x,$y"] = char
            }
        }

        val previousTotals = ArrayList<Int>()
        var period = -1
        var time = 0

        while (true) {
            time++
            val newMap = HashMap<String, Char>()

            for (x in 0 until 50) {
                for (y in 0 until 50) {
                    var trees = 0
                    var yards = 0

                    for (i in (x - 1)..(x + 1)) {
                        for (j in (y - 1)..(y + 1)) {
                            if (i != x || j != y) {
                                when (map["$i,$j"]) {
                                    '|' -> trees++
                                    '#' -> yards++
                                }
                            }
                        }
                    }

                    newMap["$x,$y"] = when (map["$x,$y"]) {
                        '.' -> if (trees >= 3) '|' else '.'
                        '|' -> if (yards >= 3) '#' else '|'
                        '#' -> if (yards >= 1 && trees >= 1) '#' else '.'
                        else -> '.'
                    }
                }
            }

            map = newMap
            val total = map.values.count { it == '|' } * map.values.count { it == '#' }

            if (time == 10) {
                part1Total = total
            }

            // Look for repeat value after a while
            if (time > 500) {
                if (period == -1) {
                    if (previousTotals.contains(total)) {
                        period = previousTotals.size
                    }
                    previousTotals.add(total)
                } else {
                    if (time % period == 1_000_000_000 % period) {
                        part2Total = total
                        break
                    }
                }
            }
        }

        return part1Total.toString()
    }

    override fun part2(): String {
        return part2Total.toString()
    }
}

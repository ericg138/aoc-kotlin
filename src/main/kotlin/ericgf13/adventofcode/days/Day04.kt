package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day04 : Day(4) {

    private val guards = HashMap<Int, MutableList<Int>>()

    override fun part1(): String {
        val pattern = """\[\d{4}-\d{2}-\d{2} (\d{2}):(\d{2})] (.+)""".toRegex()
        var currentGuard = 0
        var asleepMinute = 0

        getInputAsList().sorted().forEach {
            val matchResult = pattern.matchEntire(it)
            val (hour, minute, action) = matchResult!!.destructured

            if (action.contains("Guard")) {
                currentGuard = action.split(" ")[1].substring(1).toInt()
            }

            if (action.contains("asleep")) {
                asleepMinute = if (hour.toInt() == 23) {
                    60 - minute.toInt()
                } else {
                    minute.toInt()
                }
            }

            if (action.contains("wakes")) {
                (asleepMinute until minute.toInt()).forEach {
                    guards.getOrPut(currentGuard) { mutableListOf() }.add(it)
                }
            }
        }

        val guard = guards.maxBy { it.value.size }
        val minute = guard!!.value.groupBy { it }.maxBy { it.value.size }!!.key
        return (guard.key * minute).toString()
    }

    override fun part2(): String {
        var maxSleepOnMinute = 0
        var minute = 0
        var bestGuard = 0

        for (guard in guards) {
            val minuteMostAsleep = guard.value.groupBy { it }.maxBy { it.value.size }
            if (minuteMostAsleep!!.value.size > maxSleepOnMinute) {
                maxSleepOnMinute = minuteMostAsleep.value.size
                minute = minuteMostAsleep.key
                bestGuard = guard.key
            }
        }

        return (bestGuard * minute).toString()
    }
}

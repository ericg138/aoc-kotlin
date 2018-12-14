package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day12 : Day(12) {

    private val initialState = HashMap<Int, Char>()
    private val notes = HashMap<String, Char>()

    init {
        getInputAsList().forEachIndexed { index, line ->
            if (index == 0) {
                line.substring(15).forEachIndexed { i, char ->
                    initialState[i] = char
                }
            } else if (index >= 2) {
                notes[line.substring(0, 5)] = line[9]
            }
        }
    }

    override fun part1(): String {
        var pots = HashMap<Int, Char>(initialState)

        (1..20).forEach {
            pots = processGeneration(pots)
        }

        return calcSum(pots).toString()
    }

    override fun part2(): String {
        var pots = HashMap<Int, Char>(initialState)
        var sum = 0

        (1..1000).forEach {
            if (it == 1000) sum = calcSum(pots)
            pots = processGeneration(pots)
        }

        val newSum = calcSum(pots)
        val diff = newSum - sum
        return (newSum + (50_000_000_000 - 1000) * diff).toString()
    }

    private fun processGeneration(pots: HashMap<Int, Char>): HashMap<Int, Char> {
        val newPots = HashMap<Int, Char>()

        val minPot = pots.filter { it.value == '#' }.minBy { it.key }!!.key
        val maxPot = pots.filter { it.value == '#' }.maxBy { it.key }!!.key

        for (i in (minPot - 2)..(maxPot + 2)) {
            var combination = ""
            for (j in (i - 2)..(i + 2)) {
                combination += pots.getOrDefault(j, '.')
            }
            newPots[i] = notes[combination] ?: '.'
        }

        return newPots
    }

    private fun calcSum(pots: HashMap<Int, Char>): Int {
        return pots.entries.fold(0) { acc, v ->
            if (v.value == '#') acc + v.key else acc
        }
    }
}

package ericg138.aoc.year2022.days

import ericg138.aoc.Day
import java.util.*

class Day05 : Day(5) {
    private val input = getInputAsList().drop(10)
    private val crateLines = getInputAsList().take(8)
    private val regex = Regex("""move (\d+) from (\d+) to (\d+)""")

    override fun part1(): String {
        val crates = getCrates()

        input.forEach {
            val matchResult = regex.find(it)
            val (count, from, to) = matchResult!!.destructured

            repeat(count.toInt()) {
                crates[to.toInt()]!!.add(crates[from.toInt()]!!.last())
                crates[from.toInt()]!!.removeLast()
            }
        }

        return crates.values.map { it.last() }.joinToString("")
    }

    override fun part2(): String {
        val crates = getCrates()

        input.forEach {
            val matchResult = regex.find(it)
            val (count, from, to) = matchResult!!.destructured

            val takeLast = crates[from.toInt()]!!.takeLast(count.toInt())
            crates[to.toInt()]!!.addAll(takeLast)
            repeat(count.toInt()) {
                crates[from.toInt()]!!.removeLast()
            }
        }

        return crates.values.map { it.last() }.joinToString("")
    }

    private fun getCrates(): SortedMap<Int, MutableList<Char>> {
        val crates = mutableMapOf<Int, MutableList<Char>>().toSortedMap()
        crateLines.forEach {
            it.forEachIndexed { index, c ->
                if (c in 'A'..'Z') {
                    val crateNum = index / 4 + 1
                    crates.getOrPut(crateNum) { mutableListOf() }
                    crates[crateNum]!!.add(0, c)
                }
            }
        }
        return crates
    }
}

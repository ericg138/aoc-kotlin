package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day08 : Day(8) {
    private val input = getInputAsString().split(" ").map { it.toInt() }

    data class Node(val size: Int, val value: Int)

    override fun part1(): String {
        val entries = mutableListOf<Int>()
        process(input, entries)
        return entries.sum().toString()
    }

    override fun part2(): String {
        return process(input, mutableListOf()).value.toString()
    }

    private fun process(list: List<Int>, entries: MutableList<Int>): Node {
        val childCount = list[0]
        val entriesCount = list[1]
        var size = 2
        var value = 0
        val map = HashMap<Int, Int>()

        for (i in 1..childCount) {
            val node = process(list.subList(size, list.size), entries)
            size += node.size
            map[i] = node.value
        }

        for (i in size until size + entriesCount) {
            val entry = list[i]
            entries.add(entry)
            value += if (childCount == 0) {
                entry
            } else {
                map[entry] ?: 0
            }
        }

        return Node(size + entriesCount, value)
    }
}

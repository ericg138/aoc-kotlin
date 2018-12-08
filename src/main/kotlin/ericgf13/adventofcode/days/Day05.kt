package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day
import java.util.*

class Day05 : Day(5) {
    private val input = getInputAsString()

    override fun part1(): String {
        return sizeAfterReact(input.toList()).toString()
    }

    override fun part2(): String {
        val map = HashMap<Char, Int>()

        for (i in 'a'..'z') {
            val list = input.toMutableList()
            list.removeIf { it.toLowerCase() == i.toLowerCase() }
            map[i] = sizeAfterReact(list)
        }

        return map.minBy { it.value }?.value.toString()
    }

    private fun sizeAfterReact(list: List<Char>): Int {
        val deque = ArrayDeque<Char>()

        list.forEach {
            if (deque.size == 0) {
                deque.push(it)
            } else {
                val inQueue = deque.peek()
                if (it != inQueue && it.toLowerCase() == inQueue.toLowerCase()) {
                    deque.pop()
                } else {
                    deque.push(it)
                }
            }
        }

        return deque.size
    }
}

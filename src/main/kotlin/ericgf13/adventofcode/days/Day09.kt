package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day
import java.util.*

class Day09 : Day(9) {

    private val players = 468
    private val lastMarble = 71010L

    override fun part1(): String {
        return process(lastMarble)
    }

    override fun part2(): String {
        return process(lastMarble * 100)
    }

    private fun process(last: Long): String {
        val scores = HashMap<Int, Long>()
        var currentPlayer = 0

        val marbles = LinkedList<Long>(listOf(0))
        var iterator = marbles.listIterator(1)

        for (marble in 1..last) {
            if (marble % 23 == 0L) {
                (0..7).forEach {
                    if (iterator.hasPrevious()) {
                        iterator.previous()
                    } else {
                        iterator = marbles.listIterator(marbles.size - 1)
                    }
                }

                scores[currentPlayer] = scores.getOrDefault(currentPlayer, 0) + iterator.next() + marble
                iterator.remove()
                iterator.next()
            } else {
                if (iterator.hasNext()) {
                    iterator.next()
                } else {
                    iterator = marbles.listIterator(1)
                }
                iterator.add(marble)
            }

            currentPlayer = (currentPlayer + 1) % players
        }

        return scores.maxBy { it.value }?.value.toString()
    }
}

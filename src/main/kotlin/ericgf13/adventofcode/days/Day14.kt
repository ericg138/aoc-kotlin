package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day14 : Day(14) {

    private val input = "306281"

    override fun part1(): String {
        val scores = mutableListOf(3, 7)

        var index1 = 0
        var index2 = 1

        while (scores.size < input.toInt() + 10) {
            val score1 = scores[index1]
            val score2 = scores[index2]

            val sum = score1 + score2
            if (sum >= 10) scores.add(1)
            scores.add(sum % 10)

            index1 = (index1 + 1 + score1) % scores.size
            index2 = (index2 + 1 + score2) % scores.size
        }

        return scores.subList(input.toInt(), input.toInt() + 10).joinToString("")
    }

    override fun part2(): String {
        val scores = mutableListOf(3, 7)

        var index1 = 0
        var index2 = 1

        while (!scores.takeLast(10).joinToString("").contains(input)) {
            val score1 = scores[index1]
            val score2 = scores[index2]

            val sum = score1 + score2
            if (sum >= 10) scores.add(1)
            scores.add(sum % 10)

            index1 = (index1 + 1 + score1) % scores.size
            index2 = (index2 + 1 + score2) % scores.size
        }

        return scores.joinToString("").indexOf(input).toString()
    }
}

package ericg138.aoc.year2018.days

import ericg138.aoc.Day

class Day07 : Day(7) {

    data class Worker(var available: Boolean) {
        var letter = '0'
        var timeLeft = 0
    }

    private fun parseInput(): HashMap<Char, MutableList<Char>> {
        val map = HashMap<Char, MutableList<Char>>()
        getInputAsList().forEach {
            val letter1 = it[5]
            val letter2 = it[36]
            map.putIfAbsent(letter1, mutableListOf())
            map.getOrPut(letter2) { mutableListOf() }.add(letter1)
        }
        return map
    }

    override fun part1(): String {
        val map = parseInput()

        var out = ""
        while (map.isNotEmpty()) {
            val char = map.filter { it.value.isEmpty() }.map { it.key }.minOf { it }
            out += char
            map.forEach { it.value.remove(char) }
            map.remove(char)
        }

        return out
    }

    override fun part2(): String {
        val map = parseInput()

        val workers = mutableListOf<Worker>()
        (0..4).forEach { workers.add(Worker(true)) }

        var seconds = -1
        while (map.isNotEmpty()) {
            seconds++

            workers.filterNot { it.available }.forEach { worker ->
                worker.timeLeft--
                if (worker.timeLeft == 0) {
                    worker.available = true
                    map.forEach { it.value.remove(worker.letter) }
                    map.remove(worker.letter)
                }
            }

            val availableLetters = map.filter { it.value.isEmpty() }.map { it.key }.sorted()
            availableLetters.forEach { letter ->
                if (workers.none { it.letter == letter }) {
                    val worker = workers.firstOrNull { it.available }
                    if (worker != null) {
                        worker.letter = letter
                        worker.timeLeft = letter.code - 64 + 60
                        worker.available = false
                    }
                }
            }
        }

        return seconds.toString()
    }
}

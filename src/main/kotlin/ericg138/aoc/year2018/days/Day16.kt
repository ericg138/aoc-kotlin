package ericg138.aoc.year2018.days

import ericg138.aoc.Day

class Day16 : Day(16) {

    data class Sample(val before: List<Int>, val after: List<Int>, val instruction: List<Int>)

    private val samples = mutableSetOf<Sample>()
    private val testProgram = mutableListOf<List<Int>>()
    private val operations = hashMapOf(
            ::addr to (0..15).toMutableSet(),
            ::addi to (0..15).toMutableSet(),
            ::mulr to (0..15).toMutableSet(),
            ::muli to (0..15).toMutableSet(),
            ::banr to (0..15).toMutableSet(),
            ::bani to (0..15).toMutableSet(),
            ::borr to (0..15).toMutableSet(),
            ::bori to (0..15).toMutableSet(),
            ::setr to (0..15).toMutableSet(),
            ::seti to (0..15).toMutableSet(),
            ::gtir to (0..15).toMutableSet(),
            ::gtri to (0..15).toMutableSet(),
            ::gtrr to (0..15).toMutableSet(),
            ::eqir to (0..15).toMutableSet(),
            ::eqri to (0..15).toMutableSet(),
            ::eqrr to (0..15).toMutableSet())

    override fun part1(): String {
        var isSample = false
        var before = listOf<Int>()
        var instruction = listOf<Int>()

        getInputAsList().filterNot { it.isBlank() }.forEach { line ->
            if (line.contains("Before")) {
                isSample = true
                before = line.slice(9..18).replace(" ", "").split(",").map { it.toInt() }
            } else if (isSample) {
                if (line.contains("After")) {
                    samples.add(Sample(before, line.slice(9..18).replace(" ", "").split(",").map { it.toInt() }, instruction))
                    isSample = false
                } else {
                    instruction = line.split(" ").map { it.toInt() }
                }
            } else {
                testProgram.add(line.split(" ").map { it.toInt() })
            }
        }

        var count = 0

        samples.forEach { sample ->
            var match = 0

            for (operation in operations) {
                val result = ArrayList(sample.before)
                result[sample.instruction[3]] = operation.key(sample.before, sample.instruction[1], sample.instruction[2])
                if (result == sample.after) {
                    match++
                } else {
                    operation.value.remove(sample.instruction[0])
                }
            }

            if (match >= 3) {
                count++
            }
        }

        return count.toString()
    }

    override fun part2(): String {
        while (operations.any { it.value.size > 1 }) {
            operations.values.filter { it.size == 1 }.forEach { loneValue ->
                operations.values.filter { it.size > 1 }.forEach { values ->
                    values.remove(loneValue.iterator().next())
                }
            }
        }

        val registers = mutableListOf(0, 0, 0, 0)
        for (instruction in testProgram) {
            val operation = operations.filter { it.value.iterator().next() == instruction[0] }.iterator().next().key
            registers[instruction[3]] = operation(registers, instruction[1], instruction[2])
        }

        return registers[0].toString()
    }

    private fun addr(reg: List<Int>, a: Int, b: Int) = reg[a] + reg[b]
    private fun addi(reg: List<Int>, a: Int, b: Int) = reg[a] + b
    private fun mulr(reg: List<Int>, a: Int, b: Int) = reg[a] * reg[b]
    private fun muli(reg: List<Int>, a: Int, b: Int) = reg[a] * b
    private fun banr(reg: List<Int>, a: Int, b: Int) = reg[a] and reg[b]
    private fun bani(reg: List<Int>, a: Int, b: Int) = reg[a] and b
    private fun borr(reg: List<Int>, a: Int, b: Int) = reg[a] or reg[b]
    private fun bori(reg: List<Int>, a: Int, b: Int) = reg[a] or b

    @Suppress("UNUSED_PARAMETER")
    private fun setr(reg: List<Int>, a: Int, b: Int) = reg[a]

    @Suppress("UNUSED_PARAMETER")
    private fun seti(reg: List<Int>, a: Int, b: Int) = a
    private fun gtir(reg: List<Int>, a: Int, b: Int) = if (a > reg[b]) 1 else 0
    private fun gtri(reg: List<Int>, a: Int, b: Int) = if (reg[a] > b) 1 else 0
    private fun gtrr(reg: List<Int>, a: Int, b: Int) = if (reg[a] > reg[b]) 1 else 0
    private fun eqir(reg: List<Int>, a: Int, b: Int) = if (a == reg[b]) 1 else 0
    private fun eqri(reg: List<Int>, a: Int, b: Int) = if (reg[a] == b) 1 else 0
    private fun eqrr(reg: List<Int>, a: Int, b: Int) = if (reg[a] == reg[b]) 1 else 0
}

package ericg138.aoc

import java.io.File
import kotlin.system.measureTimeMillis

abstract class Day(private val dayNum: Int) {
    abstract fun part1(): String
    abstract fun part2(): String

    protected fun getInputAsList(): List<String> = getFile().readLines()
    protected fun getInputAsString(): String = getFile().readText()
    private fun getFile(): File = File(this.javaClass.getResource("/${getYear()}/day${dayNum.toString().padStart(2, '0')}.txt")!!.toURI())
    private fun getYear(): String = this.javaClass.packageName.split(".")[2]
    fun execute() {
        println("===== DAY $dayNum =====")
        println("${measureTimeMillis { print("Part 1: ${part1()} - ") }} ms")
        println("${measureTimeMillis { print("Part 2: ${part2()} - ") }} ms")
    }
}

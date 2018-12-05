package ericgf13

import java.io.File

abstract class Day {
    abstract val day: Int
    abstract fun part1(): String
    abstract fun part2(): String

    protected fun getInputAsList(): List<String> = getFile().readLines()
    protected fun getInputAsString(): String = getFile().readText()
    private fun getFile(): File = File(this.javaClass.getResource("/day${day.toString().padStart(2, '0')}.txt").toURI())
}

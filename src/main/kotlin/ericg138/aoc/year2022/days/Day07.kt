package ericg138.aoc.year2022.days

import ericg138.aoc.Day

class Day07 : Day(7) {
    private val input = getInputAsList()
    private val sizes = mutableListOf<Long>()

    init {
        var mainDir: Directory? = null
        var currentDir: Directory? = null

        input.forEach {
            if (it == "$ cd ..") {
                currentDir = currentDir!!.parent
            } else if (it.startsWith("$ cd")) {
                val dirName = it.substring(5)
                val newDir = Directory(dirName, currentDir)
                if (currentDir != null) {
                    currentDir!!.directories.add(newDir)
                }
                if (dirName == "/") {
                    mainDir = newDir
                }
                currentDir = newDir
            } else if (it[0].isDigit()) {
                val split = it.split(" ")
                currentDir!!.files.add(File(split[1], split[0].toInt()))
            }
        }

        evaluateSize(mainDir!!, sizes)
    }

    private fun evaluateSize(dir: Directory, sizes: MutableList<Long>): Long {
        val dirSize = dir.files.sumOf { it.size } + dir.directories.sumOf { evaluateSize(it, sizes) }
        sizes.add(dirSize)
        return dirSize
    }

    override fun part1(): String {
        return sizes.filter { it <= 100000 }.sum().toString()
    }

    override fun part2(): String {
        val unused = 70000000 - sizes.last()
        val spaceNeeded = 30000000 - unused
        return sizes.filter { it >= spaceNeeded }.minOf { it }.toString()
    }

    data class File(var name: String, var size: Int)
    data class Directory(var name: String, var parent: Directory?, var directories: MutableList<Directory> = mutableListOf(), var files: MutableList<File> = mutableListOf())
}

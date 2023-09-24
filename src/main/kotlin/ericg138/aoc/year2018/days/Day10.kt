package ericg138.aoc.year2018.days

import ericg138.aoc.Day

class Day10 : Day(10) {

    private data class Point(var x: Int, var y: Int, val vx: Int, val vy: Int)

    var seconds = 0

    override fun part1(): String {
        val pattern = """position=<(.+),(.+)> velocity=<(.+),(.+)>""".toRegex()
        val points = getInputAsList().map {
            val matchResult = pattern.matchEntire(it)
            val (x, y, vx, vy) = matchResult!!.destructured
            Point(x.trim().toInt(), y.trim().toInt(), vx.trim().toInt(), vy.trim().toInt())
        }.toCollection(mutableListOf())

        while (true) {
            seconds++

            points.forEach {
                it.x += it.vx
                it.y += it.vy
            }

            val minX = points.minBy { it.x }.x
            val maxX = points.maxBy { it.x }.x
            val minY = points.minBy { it.y }.y
            val maxY = points.maxBy { it.y }.y

            if (maxY - minY < 10) {
                for (i in minY..maxY) {
                    for (j in minX..maxX) {
                        if (points.any { it.y == i && it.x == j }) {
                            print("#")
                        } else {
                            print(".")
                        }
                    }
                    print("\n")
                }
                break
            }
        }

        return "printed above"
    }

    override fun part2(): String {
        return seconds.toString()
    }
}

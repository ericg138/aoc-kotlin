package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day03 : Day(3) {
    private val pattern = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()
    private val input = getInputAsList().map {
        val matchResult = pattern.matchEntire(it)
        val (id, x, y, width, height) = matchResult!!.destructured
        Area(id.toInt(), x.toInt(), y.toInt(), width.toInt(), height.toInt())
    }

    data class Area(val id: Int, val x: Int, val y: Int, val width: Int, val height: Int)

    override fun part1(): String {
        val idsByCoord = HashMap<String, MutableSet<Int>>()

        input.forEach {
            for (i in it.x until it.x + it.width) {
                for (j in it.y until it.y + it.height) {
                    val coord = "$i,$j"
                    idsByCoord.putIfAbsent(coord, mutableSetOf())
                    idsByCoord[coord]?.add(it.id)
                }
            }
        }

        return idsByCoord.filter { it.value.size > 1 }.count().toString()
    }

    override fun part2(): String {
        val idByCoord = HashMap<String, Int>()
        val ids = input.map { it.id }.toMutableSet()

        input.forEach {
            for (i in it.x until it.x + it.width) {
                for (j in it.y until it.y + it.height) {
                    val coord = "$i,$j"
                    val id = idByCoord.getOrPut(coord) { it.id }
                    if (id != it.id) {
                        ids.remove(id)
                        ids.remove(it.id)
                    }
                }
            }
        }

        return ids.first().toString()
    }
}

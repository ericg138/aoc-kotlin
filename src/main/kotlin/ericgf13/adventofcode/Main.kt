package ericgf13.adventofcode

import ericgf13.adventofcode.days.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val days = mutableListOf<Day>().apply {
        add(Day01())
        add(Day02())
        add(Day03())
        add(Day05())
    }

    days.forEach {
        val sb = StringBuilder()
        sb.appendln("===== DAY ${it.dayNum} =====")
        sb.appendln(" - " + measureTimeMillis { sb.append("Part 1: ${it.part1()}") } + " ms")
        sb.appendln(" - " + measureTimeMillis { sb.append("Part 2: ${it.part2()}") } + " ms")
        print(sb)
    }
}

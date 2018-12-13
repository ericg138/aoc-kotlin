package ericgf13.adventofcode

import ericgf13.adventofcode.days.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val days = mutableListOf<Day>().apply {
        add(Day01())
        add(Day02())
        add(Day03())
        add(Day04())
        add(Day05())
        add(Day08())
        add(Day09())
        add(Day10())
        add(Day11())
        add(Day13())
    }

    days.forEach {
        val sb = StringBuilder()
        sb.appendln("===== DAY ${it.dayNum} =====")
        sb.appendln(" - " + measureTimeMillis { sb.append("Part 1: ${it.part1()}") } + " ms")
        sb.appendln(" - " + measureTimeMillis { sb.append("Part 2: ${it.part2()}") } + " ms")
        print(sb)
    }
}

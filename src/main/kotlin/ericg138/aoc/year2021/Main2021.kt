package ericg138.aoc.year2021

import ericg138.aoc.Day
import ericg138.aoc.year2021.days.*

fun main() {
    mutableListOf(
            Day01(),
            Day02(),
            Day03(),
            Day04(),
            Day05(),
            Day06(),
            Day07(),
            Day08(),
            Day09(),
            Day10(),
            Day11(),
            Day12(),
            Day13(),
            Day14(),
            Day15()
    ).forEach(Day::execute)
}

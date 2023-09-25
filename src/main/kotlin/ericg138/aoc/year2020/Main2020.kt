package ericg138.aoc.year2020

import ericg138.aoc.Day
import ericg138.aoc.year2020.days.*

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
            Day11(),
            Day12(),
            Day15()
    ).forEach(Day::execute)
}

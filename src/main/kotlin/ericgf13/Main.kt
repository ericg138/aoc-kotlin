package ericgf13

import java.io.File

fun main(args: Array<String>) {
    val days = Days()
    days.day1()
}

class Days {

    private fun getInputAsList(day: Int): List<String> = File(this.javaClass.getResource("/day$day.txt").toURI()).readLines()

    fun day1() {
        println("===== DAY 1 =====")
        val input = getInputAsList(1)

        // Part 1
        println(input.fold(0) { acc, v ->
            acc + v.toInt()
        })

        // Part 2
        var value = 0
        var found = false
        val foundValues = HashSet<Int>()

        do {
            input.forEach {
                if (!found) {
                    value += it.toInt()
                    found = !foundValues.add(value)
                }
            }
        } while (!found)

        println(value)
    }
}

package ericg138.aoc.year2018.days

import ericg138.aoc.Day

class Day13 : Day(13) {

    enum class Direction {
        UP, DOWN, LEFT, RIGHT;

        fun left() = when (this) {
            UP -> LEFT
            DOWN -> RIGHT
            LEFT -> DOWN
            RIGHT -> UP
        }

        fun right() = when (this) {
            UP -> RIGHT
            DOWN -> LEFT
            LEFT -> UP
            RIGHT -> DOWN
        }

        fun slash() = when (this) {
            UP -> RIGHT
            DOWN -> LEFT
            LEFT -> DOWN
            RIGHT -> UP
        }

        fun antiSlash() = when (this) {
            UP -> LEFT
            DOWN -> RIGHT
            LEFT -> UP
            RIGHT -> DOWN
        }
    }

    data class Cart(var x: Int, var y: Int, var direction: Direction) {
        var turn = 0
        var alive = true

        fun intersection() {
            when (turn) {
                0 -> direction = direction.left()
                2 -> direction = direction.right()
            }
            turn = (turn + 1) % 3
        }

        fun move() {
            when (direction) {
                Direction.UP -> y--
                Direction.DOWN -> y++
                Direction.LEFT -> x--
                Direction.RIGHT -> x++
            }
        }
    }

    private val carts = mutableListOf<Cart>()

    override fun part1(): String {
        val coords = HashMap<String, Char>()

        getInputAsList().forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                when (char) {
                    '^' -> carts.add(Cart(x, y, Direction.UP))
                    'v' -> carts.add(Cart(x, y, Direction.DOWN))
                    '>' -> carts.add(Cart(x, y, Direction.RIGHT))
                    '<' -> carts.add(Cart(x, y, Direction.LEFT))
                }
                if (char != ' ') coords["$x,$y"] = char
            }
        }

        var firstCrash = ""

        while (carts.filter { it.alive }.count() > 1) {
            carts.filter { it.alive }.sortedWith(compareBy({ it.y }, { it.x })).forEach { cart ->

                when (coords["${cart.x},${cart.y}"]) {
                    '/' -> cart.direction = cart.direction.slash()
                    '\\' -> cart.direction = cart.direction.antiSlash()
                    '+' -> cart.intersection()
                }

                cart.move()

                val crashCart = carts.firstOrNull { it.alive && it != cart && it.x == cart.x && it.y == cart.y }
                if (crashCart != null) {
                    if (firstCrash.isEmpty()) {
                        firstCrash = "${cart.x},${cart.y}"
                    }
                    crashCart.alive = false
                    cart.alive = false
                }
            }
        }

        return firstCrash
    }

    override fun part2(): String {
        val lastCart = carts.first { it.alive }
        return "${lastCart.x},${lastCart.y}"
    }
}

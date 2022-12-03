package dayXX

import readInput

fun main() {
    fun parse(input: List<String>) = input

    fun part1(input: List<String>): Int =
        input.size

    fun part2(input: List<String>): Int =
        input.size


    val input = readInput("DayXX")
    val parsedInputs = parse(input)
    println(part1(parsedInputs))
    println(part2(parsedInputs))
}

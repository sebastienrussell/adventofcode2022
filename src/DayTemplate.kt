fun main() {

    fun part1(input: List<String>): Int =
        input.size

    fun part2(input: List<String>): Int =
        input.size

    val testInput = readInput("DayXX_test")
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)

    val input = readInput("DayXX")
    println(part1(input))
    println(part2(input))
}


fun main() {

    fun part1(input: List<String>): Int =
        input.size

    fun part2(input: List<String>): Int =
        input.size

    val testInput = readInput("Day${DAY_NUMBER}_test")
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)

    val input = readInput("Day$DAY_NUMBER")
    println(part1(input))
    println(part2(input))
}

private const val DAY_NUMBER = "06"

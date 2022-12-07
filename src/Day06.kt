fun main() {

    fun part1(input: String): Int =
        input.findMarkerContentByMarkerSize(4)

    fun part2(input: String): Int =
        input.findMarkerContentByMarkerSize(14)

    TEST_CASES_PART1.forEach { (input, answer) ->
        val result = part1(input)
        check(result == answer)
    }
    TEST_CASES_PART2.forEach { (input, answer) ->
        val result = part2(input)
        check(result == answer)
    }

    val input = readInput("Day$DAY_NUMBER")[0]
    println(part1(input))
    println(part2(input))
}

private const val DAY_NUMBER = "06"
private val TEST_CASES_PART1 = listOf(
    Pair("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 7),
    Pair("bvwbjplbgvbhsrlpgdmjqwftvncz", 5),
    Pair("nppdvjthqldpwncqszvftbrmjlhg", 6),
    Pair("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10),
    Pair("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11)
)

private val TEST_CASES_PART2 = listOf(
    Pair("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 19),
    Pair("bvwbjplbgvbhsrlpgdmjqwftvncz", 23),
    Pair("nppdvjthqldpwncqszvftbrmjlhg", 23),
    Pair("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 29),
    Pair("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 26)
)

private fun String.findMarkerContentByMarkerSize(markerSize: Int) =
    windowed(markerSize).indexOfFirst { it.toSet().size == it.length } + markerSize
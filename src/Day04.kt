fun main() {
    fun parsePairs(input: List<String>) = input.map { elf ->
        elf.split(',').map {
            val sections = it.split('-')
            sections[0].toInt()..sections[1].toInt()
        }
    }

    fun part1(input: List<String>): Int =
        parsePairs(input).count { pair ->
            (pair[0].first <= pair[1].first && pair[0].last >= pair[1].last) ||
                    (pair[1].first <= pair[0].first && pair[1].last >= pair[0].last)
        }

    fun part2(input: List<String>): Int =
        parsePairs(input).count { pair ->
            pair[0].intersect(pair[1]).isNotEmpty()
        }


    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

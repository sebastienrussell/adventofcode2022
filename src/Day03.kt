import models.PRIORITIES
import models.Rucksack
import models.RucksackGroup

fun main() {
    fun parse(input: List<String>) = input.map { Rucksack(items = it) }

    fun part1(input: List<Rucksack>): Int {
        val itemsInBoth = mutableListOf<Pair<Char, Int>>()
        input.forEach { rucksack ->
            val rucksackDivided = rucksack.divide()
            val itemInBoth = rucksackDivided[0].findCommonCharacter(rucksackDivided[1])
            itemsInBoth.add(Pair(itemInBoth, itemInBoth.getPriority()))
        }
        return itemsInBoth.sumOf { it.second }
    }

    fun parseRucksackGroups(input: List<Rucksack>): MutableList<RucksackGroup> {
        val groupsOfRucksack = mutableListOf<RucksackGroup>()
        val inputIterator = input.iterator()
        while (inputIterator.hasNext()) {
            groupsOfRucksack.add(
                RucksackGroup(
                    first = inputIterator.next(),
                    second = inputIterator.next(),
                    third = inputIterator.next()
                )
            )
        }
        return groupsOfRucksack
    }

    fun part2(input: List<Rucksack>): Int =
        parseRucksackGroups(input).sumOf { it.getPriority() }


    val input = readInput("Day03")
    val parsedInputs = parse(input)
    println(part1(parsedInputs))
    println(part2(parsedInputs))
}

fun String.findCommonCharacter(other: String) =
    first { other.indexOf(it) != -1 }

fun String.findCommonCharacter(second: String, third: String) =
    first { second.indexOf(it) != -1 && third.indexOf(it) != -1 }

fun Char.getPriority() =
    PRIORITIES.indexOf(this) + 1
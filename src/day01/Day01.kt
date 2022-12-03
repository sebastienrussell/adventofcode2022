package day01

import models.Elf
import readInput

fun main() {
    fun parseElves(input: List<String>): List<Elf> {
        val elves = mutableListOf<Elf>()
        val elvesIterator = input.iterator()

        var currentElf = Elf()
        while (elvesIterator.hasNext()) {
            val calorie = elvesIterator.next()
            if (calorie.isEmpty()) {
                elves.add(currentElf)
                currentElf = Elf()
            } else {
                currentElf.addCalories(calorie.toInt())
            }
        }

        return elves
    }

    fun part1(elves: List<Elf>): Int {
        return elves.maxBy { it.totalCaloriesCarried() }.totalCaloriesCarried()
    }

    fun part2(elves: List<Elf>): Int {
        return elves.sortedByDescending { it.totalCaloriesCarried() }.slice(0..2).sumOf { it.totalCaloriesCarried() }
    }

    val input = readInput("Day01")
    val elves = parseElves(input)
    println(part1(elves))
    println(part2(elves))
}

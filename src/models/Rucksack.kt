package models

import findCommonCharacter
import getPriority

data class Rucksack(
    val items: String
) {
    fun divide() =
        listOf(
            items.substring(0 until items.length / 2),
            items.substring(items.length / 2)
        )
}

data class RucksackGroup(
    val first: Rucksack,
    val second: Rucksack,
    val third: Rucksack
) {
    fun getPriority() =
        first.items.findCommonCharacter(second.items, third.items).getPriority()
}


// find index here to get the priority
const val PRIORITIES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
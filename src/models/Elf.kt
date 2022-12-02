package models

data class Elf(
    private val calories: MutableList<Int> = mutableListOf<Int>()
) {
    fun addCalories(calorie: Int) =
        calories.add(calorie)

    fun totalCaloriesCarried(): Int {
        var total = 0
        calories.forEach { total += it }
        return total
    }
}
import kotlin.math.max

fun main() {

    fun part1(input: List<String>): Int =
        input.mapIndexed { i, row ->
            row.mapIndexed { j, char ->
                Tree(
                    size = char - SMALLEST_SIZE_TREE,
                    position = Pair(i, j)
                )
            }
        }.flatten().let { trees ->
            var visibleTrees = 0

            val rows = input.size
            val columns = input[0].length
            trees.forEach { currentTree ->
                if (currentTree.position.first == 0 || currentTree.position.second == 0 ||
                    currentTree.position.first == rows - 1 || currentTree.position.second == columns - 1
                )
                    visibleTrees++
                else {
//                    val middleTrees = trees.filter {
//                        (currentTree.position.first == it.position.first // same row
//                                || currentTree.position.second == it.position.second) // same column
//                                && currentTree != it // not this element
//                    }

                    val fromLeft = trees.filter {
                        it.position.first == currentTree.position.first &&
                                it.position.second <= currentTree.position.second
                    }.first { it.size >= currentTree.size } == currentTree

                    val fromRight = trees.filter {
                        it.position.first == currentTree.position.first &&
                                it.position.second >= currentTree.position.second
                    }.reversed().first { it.size >= currentTree.size } == currentTree

                    val fromTop = trees.filter {
                        it.position.second == currentTree.position.second &&
                                it.position.first <= currentTree.position.first
                    }.first { it.size >= currentTree.size } == currentTree

                    val fromBottom = trees.filter {
                        it.position.second == currentTree.position.second &&
                                it.position.first >= currentTree.position.first
                    }.reversed().first { it.size >= currentTree.size } == currentTree

//                    val isVisibleTree = false
                    val isVisibleTree = fromLeft || fromRight || fromTop || fromBottom
                    if (isVisibleTree)
                        visibleTrees++
                }
            }

            return visibleTrees
        }

    fun part2(input: List<String>): Int {
        var maxScenicScore = 0

        val grid = input.map { row -> row.map { it.digitToInt() } }

        for (row in 1 until grid.size - 1) {
            for (column in 1 until grid.first().size - 1) {

                val currentSize = grid[row][column]
                val currentColumnValues = grid.map { it[column] }

                var leftScenicScore = grid[row].take(column).reversed().takeWhile { it < currentSize }.count()
                if (leftScenicScore == 0 || leftScenicScore < grid[row].take(column).size)
                    leftScenicScore++

                var rightScenicScore = grid[row].drop(column + 1).takeWhile { it < currentSize }.count()
                if (rightScenicScore == 0 || rightScenicScore < grid[row].drop(column + 1).size)
                    rightScenicScore++

                var topScenicScore = currentColumnValues.take(row).reversed().takeWhile { it < currentSize }.count()
                if (topScenicScore == 0 || topScenicScore < currentColumnValues.take(row).size)
                    topScenicScore++

                var bottomScenicScore = currentColumnValues.drop(row + 1).takeWhile { it < currentSize }.count()

                if (bottomScenicScore == 0 || bottomScenicScore < currentColumnValues.drop(row + 1).size)
                    bottomScenicScore++

                val treeScenicScore = leftScenicScore * rightScenicScore * topScenicScore * bottomScenicScore
                maxScenicScore = max(treeScenicScore, maxScenicScore)
            }
        }

        return maxScenicScore
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}

data class Tree(
    val size: Int,
    val position: Pair<Int, Int>
)

const val SMALLEST_SIZE_TREE = '0'
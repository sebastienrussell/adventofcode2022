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
        var top: Int? = null
        var right: Int? = null
        var bottom: Int? = null
        var left: Int? = null

        // FirstRow
//        for (column in input[0].indices) {
//            val currentTreeLinked = TreeLinked(
//                size = input[0][column] - SMALLEST_SIZE_TREE,
//                position = Pair(0, column),
//                left = left,
//                right = right,
//                top = top,
//                bottom = bottom
//            )
//            when(column) {
//                left = currentTreeLinked
//            }
//
//            fieldOfTree.add(
//                currentTreeLinked
//            )
//        }

        val grid = input.map { row -> row.map { it.digitToInt() } }
        val height = grid.size
        val width = grid.first().size

        for (row in grid.indices) {
            for (column in grid.first().indices) {

                val currentSize = grid[row][column]
                val topIndice = row - 1
                val rightIndice = column + 1
                val bottomIndice = row + 1
                val leftIndice = column - 1

                if (topIndice in grid.indices)
                    top = grid[topIndice][column]
                if (bottomIndice in grid.indices)
                    bottom = grid[bottomIndice][column]
                if (rightIndice in grid.first().indices)
                    right = grid[row][rightIndice]
                if (leftIndice in grid.first().indices)
                    left = grid[row][leftIndice]
                
            }
        }

        return 0
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
//    println(part2(input))
}

data class Tree(
    val size: Int,
    val position: Pair<Int, Int>
)

data class TreeLinked(
    val size: Int,
    val position: Pair<Int, Int>,
    val left: TreeLinked?,
    val right: TreeLinked?,
    val top: TreeLinked?,
    val bottom: TreeLinked?
)

const val SMALLEST_SIZE_TREE = '0'
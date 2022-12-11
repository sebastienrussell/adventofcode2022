fun main() {

    fun calculateTailPositionVisited(input: List<String>, numberOfKnots: Int): Int {
        var headPos = START_POSITION
        var tailPos = START_POSITION
        val tailPositionsVisited = mutableSetOf(tailPos)
        val knotsPositions = mutableSetOf(headPos)

        repeat(numberOfKnots - 1) {
            knotsPositions.add(START_POSITION)
        }

        input.forEach { inputLine ->
            val direction = inputLine.split(" ")[0][0]
            val steps = inputLine.split(" ")[1].toInt()

            repeat(steps) {
                val oldHeadPos = headPos

                headPos = when (direction) {
                    'R' -> Pair(headPos.first, headPos.second + 1) // RIGHT
                    'L' -> Pair(headPos.first, headPos.second - 1) // LEFT
                    'D' -> Pair(headPos.first - 1, headPos.second) // DOWN
                    else -> Pair(headPos.first + 1, headPos.second) // UP
                }

                tailPos = when {
                    headPos.hasDistanceOfOne(tailPos)
                            || headPos == tailPos -> tailPos

                    else -> oldHeadPos
                }
                if (!tailPositionsVisited.contains(tailPos))
                    tailPositionsVisited.add(tailPos)
            }
        }
        return tailPositionsVisited.size
    }

    fun part1(input: List<String>): Int =
        calculateTailPositionVisited(input, 2)

    fun part2(input: List<String>): Int =
        calculateTailPositionVisited(input, 10)

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 1)
    check(part2(readInput("Day09_test1")) == 36)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}

data class Point(
    var x: Int,
    var y: Int
) {
    fun move(x: Int, y: Int) {
        this.x = x
        this.y = y
    }
}

private val START_POSITION = Pair(0, 0)
private fun Pair<Int, Int>.isPairDiagonal(secondPair: Pair<Int, Int>): Boolean {
    val isDiagonalBottomLeft = first - 1 == secondPair.first && second - 1 == secondPair.second // -1/-1
    val isDiagonalBottomRight = first - 1 == secondPair.first && second + 1 == secondPair.second // -1/+1
    val isDiagonalTopRight = first + 1 == secondPair.first && second + 1 == secondPair.second // +1/+1
    val isDiagonalTopLeft = first + 1 == secondPair.first && second - 1 == secondPair.second // +1/-1

    return isDiagonalBottomLeft || isDiagonalBottomRight || isDiagonalTopRight || isDiagonalTopLeft
}

private fun Pair<Int, Int>.hasDistanceOfOne(secondPair: Pair<Int, Int>): Boolean {
    val isLeft = first == secondPair.first && second - 1 == secondPair.second // 0/-1
    val isRight = first == secondPair.first && second + 1 == secondPair.second // 0/+1
    val isBottom = first - 1 == secondPair.first && second == secondPair.second // +1/0
    val isTop = first + 1 == secondPair.first && second == secondPair.second // +1/0

    return isLeft || isRight || isBottom || isTop || isPairDiagonal(secondPair)
}

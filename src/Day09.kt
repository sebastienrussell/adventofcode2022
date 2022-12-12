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
                    'R' -> Point(headPos.x, headPos.y + 1) // RIGHT
                    'L' -> Point(headPos.x, headPos.y - 1) // LEFT
                    'D' -> Point(headPos.x - 1, headPos.y) // DOWN
                    else -> Point(headPos.x + 1, headPos.y) // UP
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
//    check(part2(testInput) == 1)
//    check(part2(readInput("Day09_test1")) == 36)

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

    fun isPointDiagonal(secondPoint: Point): Boolean {
        val isDiagonalBottomLeft = x - 1 == secondPoint.x && y - 1 == secondPoint.y // -1/-1
        val isDiagonalBottomRight = x - 1 == secondPoint.x && y + 1 == secondPoint.y // -1/+1
        val isDiagonalTopRight = x + 1 == secondPoint.x && y + 1 == secondPoint.y // +1/+1
        val isDiagonalTopLeft = x + 1 == secondPoint.x && y - 1 == secondPoint.y // +1/-1

        return isDiagonalBottomLeft || isDiagonalBottomRight || isDiagonalTopRight || isDiagonalTopLeft
    }

    fun hasDistanceOfOne(secondPoint: Point): Boolean {
        val isLeft = x == secondPoint.x && y - 1 == secondPoint.y // 0/-1
        val isRight = x == secondPoint.x && y + 1 == secondPoint.y // 0/+1
        val isBottom = x - 1 == secondPoint.x && y == secondPoint.y // +1/0
        val isTop = x + 1 == secondPoint.x && y == secondPoint.y // +1/0

        return isLeft || isRight || isBottom || isTop || isPointDiagonal(secondPoint)
    }
}

private val START_POSITION = Point(0, 0)

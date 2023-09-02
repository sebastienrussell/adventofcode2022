fun main() {

    fun part1(input: List<String>): Int {
        var steps = 0

        var start = Point(0, 0)
        var end = Point(0, 0)

        val grid = input.mapIndexed { row, line ->
            line.mapIndexed { column, c ->
                if (c == 'S') {
                    start.x = row
                    start.y = column
                }
                if (c == 'E') {
                    end.x = row
                    end.y = column
                }
                c
            }
        }

        for (row in grid.indices) {
            for (col in grid.first().indices) {
                val current = grid[row][col]
                val currentCol = input.map { it[col] }
                
            }
        }

        return steps
    }

    fun part2(input: List<String>): Int =
        input.size

    val testInput = readInput("Day12_test")
    check(part1(testInput) == 31)
    check(part2(testInput) == 0)

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}


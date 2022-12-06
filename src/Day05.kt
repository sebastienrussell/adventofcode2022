import java.io.File

fun main() {
    fun parseInitialBoard(board: List<String>): MutableMap<Int, ArrayDeque<Char>> {
        val columnsOfDeque = mutableMapOf<Int, ArrayDeque<Char>>()
        board.last().forEachIndexed { index, c ->
            if (c.isDigit())
                columnsOfDeque[c - '1' + 1] = ArrayDeque(
                    board.take(board.size - 1).reversed().map {
                        if (index < it.length - 1)
                            it[index]
                        else
                            ' '
                    }.filter { it != ' ' }
                )
        }
        return columnsOfDeque
    }

    fun parseMoves(moves: List<String>) = moves.map {
        val stringSplit = it.replace("\r", "").split(" ")
        Triple(
            stringSplit[1].toInt(), // number of elements to move
            stringSplit[3].toInt(), // column to move from
            stringSplit[5].toInt(), // column to move to
        )
    }

    fun part1(input: Pair<List<String>, List<String>>): String =
        input.let { (board, moves) ->
            val columnsOfDeque = parseInitialBoard(board)

            parseMoves(moves).forEach { (number, from, to) ->
                if (columnsOfDeque.containsKey(from)
                    && columnsOfDeque.containsKey(to)
                )
                    repeat(number) {
                        val elementFrom = columnsOfDeque[from]!!.last()
                        columnsOfDeque[to]!!.addLast(elementFrom)
                        columnsOfDeque[from]!!.removeLast()
                    }
            }

            return columnsOfDeque.toAnswer()
        }


    fun part2(input: Pair<List<String>, List<String>>): String =
        input.let { (board, moves) ->
            val columnsOfDeque = parseInitialBoard(board)

            parseMoves(moves).forEach { (number, from, to) ->
                if (columnsOfDeque.containsKey(from)
                    && columnsOfDeque.containsKey(to)
                ) {
                    val list = mutableListOf<Char>()
                    repeat(number) {
                        list.add(columnsOfDeque[from]!!.last())
                        columnsOfDeque[from]!!.removeLast()
                    }

                    list.reversed().forEach {
                        columnsOfDeque[to]!!.addLast(it)
                    }
                }
            }

            return columnsOfDeque.toAnswer()
        }


    val testInput = parseBoardAndMoves("Day05_test")
    val result = part1(testInput)
    check(result == "CMZ")
    check(part2(testInput) == "MCD")

    val input = parseBoardAndMoves("Day05")
    println(part1(input))
    println(part2(input))
}

private fun MutableMap<Int, ArrayDeque<Char>>.toAnswer(): String =
    this.flatMap { listOf(it.value.last()) }.toCharArray().concatToString()

private fun parseBoardAndMoves(name: String) =
    File("src/$name.txt").readText().split("\r\n\r\n").let {
        Pair(it[0].splitNewLine(), it[1].splitNewLine())
    }

private fun String.splitNewLine() =
    split("\n")

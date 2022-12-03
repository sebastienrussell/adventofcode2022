package day02

import models.Hand
import models.PairOfHands
import models.RoundOutcome
import models.Sign
import readInput

fun main() {
    fun parseHands(input: List<String>): List<PairOfHands> =
        input.map {
            val separatedHands = it.split(SEPARATOR)
            PairOfHands(
                opponent = Hand(Sign.from(separatedHands.first())),
                mine = Hand(Sign.from(separatedHands.last()))
            )
        }

    fun parseHandsForOutcome(input: List<String>): List<PairOfHands> =
        input.map {
            val separatedHands = it.split(SEPARATOR)
            val opponentSign = Sign.from(separatedHands.first())
            PairOfHands(
                opponent = Hand(opponentSign),
                mine = Hand(
                    Sign.forOutcome(
                        opponentSign = opponentSign,
                        outcome = when (separatedHands.last()) {
                            "Y" -> RoundOutcome.DRAW
                            "Z" -> RoundOutcome.WIN
                            else -> RoundOutcome.LOST
                        }
                    )
                )
            )
        }

    fun part1(hands: List<PairOfHands>): Int =
        hands.sumOf { it.getScore() }

    fun part2(hands: List<PairOfHands>): Int =
        hands.sumOf { it.getScore() }

    val input = readInput("Day02")
    println(part1(hands = parseHands(input)))
//    println(part1v2(input))
    println(part2(hands = parseHandsForOutcome(input)))
}

//fun part1v2(input: List<String>) =
//    input.sumOf {
//        when (it) {
//            "A X" -> 4
//            "A Y" -> 8
//            "A Z" -> 3
//            "B X" -> 1
//            "B Y" -> 5
//            "B Z" -> 9
//            "C X" -> 7
//            "C Y" -> 2
//            "C Z" -> 6
//            else -> 0
//        }.toInt()
//    }


const val SEPARATOR = ' '

package models

import models.RoundOutcome.*

enum class Sign(val points: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3),
    UNKNOWN(0);

    fun getOutcome(opponentSign: Sign) =
        when {
            opponentSign == ROCK && this == PAPER -> WIN
            opponentSign == PAPER && this == SCISSORS -> WIN
            opponentSign == SCISSORS && this == ROCK -> WIN
            opponentSign == this -> DRAW
            else -> LOST
        }

    companion object {
        fun from(letter: String) =
            when (letter) {
                "A", "X" -> ROCK
                "B", "Y" -> PAPER
                "C", "Z" -> SCISSORS
                else -> UNKNOWN
            }

        fun forOutcome(opponentSign: Sign, outcome: RoundOutcome): Sign =
            when {
                outcome == WIN && opponentSign == ROCK -> PAPER
                outcome == WIN && opponentSign == PAPER -> SCISSORS
                outcome == WIN && opponentSign == SCISSORS -> ROCK

                outcome == LOST && opponentSign == ROCK -> SCISSORS
                outcome == LOST && opponentSign == PAPER -> ROCK
                outcome == LOST && opponentSign == SCISSORS -> PAPER

                outcome == DRAW -> opponentSign
                else -> opponentSign
            }

    }
}

enum class RoundOutcome(val points: Int) {
    WIN(6),
    DRAW(3),
    LOST(0);
}

data class Hand(
    val sign: Sign
)

data class PairOfHands(
    val opponent: Hand,
    val mine: Hand
) {
    private fun outcome(): RoundOutcome =
        mine.sign.getOutcome(opponent.sign)

    fun getScore() =
        outcome().points + mine.sign.points
}
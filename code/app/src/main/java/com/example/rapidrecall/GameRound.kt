package com.example.rapidrecall
/**
 * GameRound
 *
 * Purpose: Represents one round of the memory game. When created, it
 * generates a random digit sequence of the chosen length, and it checks the
 * player's answer against that sequence.
 *
 * Design rationale: Contains only game rules. It has no UI or storage code
 * and does not depend on Android, so it can be tested on its own. check()
 * returns an Attempt instead of saving it; GameController decides what to do
 * with the result.
 *
 * Outstanding issues: None known.
 */
class GameRound (val sequenceLength: Int){
    val targetSequence: String = generateSequence(sequenceLength)
    private fun generateSequence(length: Int): String {
        var result = ""
        for (i in 1..length) {
            result += (0..9).random()
        }
        return result
    }
    fun check(userInput: String): Attempt {
        val isCorrect = targetSequence == userInput
        val attempt = Attempt(sequenceLength, targetSequence, userInput, isCorrect, System.currentTimeMillis())
        return attempt
    }
}
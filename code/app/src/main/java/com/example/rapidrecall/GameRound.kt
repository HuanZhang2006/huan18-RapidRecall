package com.example.rapidrecall

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
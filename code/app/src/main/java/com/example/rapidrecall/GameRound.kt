package com.example.rapidrecall

class GameRound (val sequenceLength: Int){
    val targetSequence: String = generateSequence(sequenceLength)
    private fun generateSequence(length: Int): String {
        val charPool = "0123456789"
        return kotlin.sequences.generateSequence { charPool.random() }
            .take(length)
            .joinToString("")
    }
    fun check(userInput: String): Attempt {
        val isCorrect = targetSequence == userInput
        val attempt = Attempt(sequenceLength, targetSequence, userInput, isCorrect, System.currentTimeMillis())
        return attempt
    }
}
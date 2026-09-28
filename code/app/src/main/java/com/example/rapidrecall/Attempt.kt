package com.example.rapidrecall

data class Attempt(
    val sequenceLength: Int,
    val targetSequence: String,
    val userInput: String,
    val isCorrect: Boolean,
    val timestamp: Long
)

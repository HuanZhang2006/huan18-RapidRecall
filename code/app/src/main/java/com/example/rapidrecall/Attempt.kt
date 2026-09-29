package com.example.rapidrecall
/**
 * Attempt
 *
 * Purpose: Record of one completed attempt: the sequence length, the target
 * sequence, the player's input, whether it was correct, and when it happened.
 *
 * Design rationale: A data class with only val properties, so a record cannot
 * be changed after it is created. The timestamp is stored as Long and is only formatted for display in LogScreen, which
 * keeps presentation out of the model.
 *
 * Outstanding issues: None known.
 */
data class Attempt(
    val sequenceLength: Int,
    val targetSequence: String,
    val userInput: String,
    val isCorrect: Boolean,
    val timestamp: Long
)

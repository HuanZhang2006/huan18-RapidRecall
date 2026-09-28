package com.example.rapidrecall

import androidx.compose.runtime.mutableStateListOf

/**
 * AttemptRepository
 *
 * Purpose: Stores every completed Attempt for the current app session and
 * computes summary statistics from them.
 *
 * Design rationale: The list is private so only this class can modify it
 * (information hiding); other classes get a read-only List.
 *
 * Outstanding issues: Data is kept in memory only and is lost when the app
 * process ends. Uses Compose state so the UI refreshes automatically.
 */
class AttemptRepository {
    private val _attempts = mutableStateListOf<Attempt>()

    fun addAttempt(attempt: Attempt) {
        _attempts.add(attempt)
    }

    fun getAttempts(): List<Attempt> = _attempts

    fun getSummary(): GameSummary {
        val total = _attempts.size
        val correct = _attempts.count { it.isCorrect }
        val accuracy = if (total == 0) 0.0 else correct * 100.0 / total
        return GameSummary(total, correct, accuracy)
    }
}
package com.example.rapidrecall
/**
 * GameSummary
 *
 * Purpose: Holds the session statistics shown on the Summary screen: total
 * attempts, correct attempts and accuracy (as a percentage from 0 to 100).
 *
 * Design rationale: A read-only snapshot created by
 * AttemptRepository.getSummary().
 *
 * Outstanding issues: None known.
 */
data class GameSummary(
    val totalAttempts: Int,
    val correctAttempts: Int,
    val accuracy: Double
)

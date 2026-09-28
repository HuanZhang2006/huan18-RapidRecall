package com.example.rapidrecall

import androidx.compose.runtime.mutableStateOf

/**
 * GameController
 *
 * Purpose: Coordinates the app. Tracks which screen is shown, starts new
 * rounds, checks answers and saves attempts to the repository.
 *
 * Design rationale: The UI only reads data through getters and reports user
 * actions through methods; all state changes happen here (MVC controller).
 * GameRound and AttemptRepository do not know about each other; the
 * controller connects them.
 *
 * Screen names: "home", "select", "game", "result", "log", "summary".
 *
 * Outstanding issues: State is lost on configuration changes (e.g. rotation)
 * because the controller is not stored in a ViewModel.
 */
class GameController {
    private val repository = AttemptRepository()

    private val _currentScreen = mutableStateOf("home")
    private val _currentRound = mutableStateOf<GameRound?>(null)
    private val _lastAttempt = mutableStateOf<Attempt?>(null)


    fun getCurrentScreen(): String = _currentScreen.value

    fun getCurrentRound(): GameRound? = _currentRound.value

    fun getLastAttempt(): Attempt? = _lastAttempt.value

    fun getAttempts(): List<Attempt> = repository.getAttempts()

    fun getSummary(): GameSummary = repository.getSummary()


    fun navigateTo(screen: String) {
        _currentScreen.value = screen
    }

    fun startGame(length: Int) {
        if (length < 1 || length > 10) return
        _currentRound.value = GameRound(length)
        _lastAttempt.value = null
        _currentScreen.value = "game"
    }

    fun submitAnswer(input: String) {
        val round = _currentRound.value ?: return
        val attempt = round.check(input)
        repository.addAttempt(attempt)
        _lastAttempt.value = attempt
        _currentRound.value = null
        _currentScreen.value = "result"
    }
}
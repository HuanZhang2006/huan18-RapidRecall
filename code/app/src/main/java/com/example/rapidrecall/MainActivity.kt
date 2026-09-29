package com.example.rapidrecall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rapidrecall.ui.theme.RapidrecallTheme
/**
 * MainActivity
 *
 * Purpose: Entry point of the app. Creates the single GameController for the
 * session and hosts the Compose UI. RapidRecallApp (below) shows the screen
 * that matches the controller's current screen name.
 *
 * Design rationale: This is the only place that connects the UI to the
 * controller. Each screen receives plain data and lambda callbacks, so the
 * screens never access GameController directly (separation of concerns).
 *
 * Outstanding issues:
 * - The system back button closes the app instead of returning to the home
 *   screen.
 * - The controller is created by the Activity, so all session data is lost on
 *   configuration changes such as screen rotation.
 */
class MainActivity : ComponentActivity() {
    private val controller = GameController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RapidrecallTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RapidRecallApp(
                        controller = controller,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun RapidRecallApp(controller: GameController, modifier: Modifier = Modifier) {
    val goHome = { controller.navigateTo("home") }

    when (controller.getCurrentScreen()) {
        "home" -> HomeScreen(
            onStart = { controller.navigateTo("select") },
            onLog = { controller.navigateTo("log") },
            onSummary = { controller.navigateTo("summary") },
            modifier = modifier
        )

        "select" -> LengthSelectScreen(
            onStartGame = { length -> controller.startGame(length) },
            onBack = goHome,
            modifier = modifier
        )

        "game" -> {
            val round = controller.getCurrentRound()
            if (round != null) {
                GameScreen(
                    targetSequence = round.targetSequence,
                    onSubmit = { input -> controller.submitAnswer(input) },
                    modifier = modifier
                )
            }
        }

        "result" -> {
            val attempt = controller.getLastAttempt()
            if (attempt != null) {
                ResultScreen(
                    attempt = attempt,
                    onPlayAgain = { controller.startGame(attempt.sequenceLength) },
                    onHome = goHome,
                    modifier = modifier
                )
            }
        }

        "log" -> LogScreen(
            attempts = controller.getAttempts(),
            onBack = goHome,
            modifier = modifier
        )

        "summary" -> SummaryScreen(
            summary = controller.getSummary(),
            onBack = goHome,
            modifier = modifier
        )
    }
}
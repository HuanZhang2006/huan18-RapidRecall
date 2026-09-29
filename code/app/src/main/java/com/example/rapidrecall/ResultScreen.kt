package com.example.rapidrecall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * ResultScreen – shows whether the attempt was correct and compares the
 * target sequence with the player's input.
 */
@Composable
fun ResultScreen(
    attempt: Attempt,
    onPlayAgain: () -> Unit,
    onHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val resultColor = if (attempt.isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828)

    Column(
        modifier = modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (attempt.isCorrect) "Correct!" else "Incorrect",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = resultColor
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text("Correct sequence", fontSize = 14.sp)
        Text(attempt.targetSequence, fontSize = 32.sp, letterSpacing = 4.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Your input", fontSize = 14.sp)
        Text(
            text = attempt.userInput.ifEmpty { "(empty)" },
            fontSize = 32.sp,
            letterSpacing = 4.sp,
            color = resultColor
        )

        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = onPlayAgain, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text("Play Again (${attempt.sequenceLength} digits)")
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(onClick = onHome, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text("Home")
        }
    }
}
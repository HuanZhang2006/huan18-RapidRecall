package com.example.rapidrecall

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * GameScreen
 *
 * Purpose: Shows the target digits one at a time, then lets the player type
 * the sequence they remember.
 *
 * Design rationale: Display timing is a UI concern, so it lives here and not
 * in the model. The screen only reports the final input through onSubmit.
 */
@Composable
fun GameScreen(
    targetSequence: String,
    onSubmit: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isShowing by remember { mutableStateOf(true) }
    var shownDigit by remember { mutableStateOf("") }
    var shownIndex by remember { mutableStateOf(0) }
    var input by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        delay(800)                                  // "Get ready..."
        for (i in targetSequence.indices) {
            shownIndex = i + 1
            shownDigit = targetSequence[i].toString()
            delay(900)                              // digit visible
            shownDigit = ""
            delay(300)                              // short blank gap
        }
        isShowing = false
    }

    Column(
        modifier = modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isShowing) {
            Text(
                text = if (shownIndex == 0) "Get ready..." else "Digit $shownIndex of ${targetSequence.length}",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(targetState = shownDigit, label = "digit") { digit ->
                    Text(
                        text = digit,
                        fontSize = 96.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            LinearProgressIndicator(
                progress = { shownIndex / targetSequence.length.toFloat() },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text("Enter the ${targetSequence.length}-digit sequence", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = input,
                onValueChange = { newValue ->
                    if (newValue.length <= targetSequence.length &&
                        newValue.all { it.isDigit() }
                    ) {
                        input = newValue
                    }
                },
                label = { Text("Your answer") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { onSubmit(input) },
                enabled = input.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit", fontSize = 18.sp)
            }
        }
    }
}
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
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

/**
 * LengthSelectScreen – lets the player choose a sequence length from 1 to 10.
 */
@Composable
fun LengthSelectScreen(
    onStartGame: (Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var length by remember { mutableFloatStateOf(5f) }

    Column(
        modifier = modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Choose sequence length", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text("${length.roundToInt()} digits", fontSize = 48.sp, fontWeight = FontWeight.Bold)

        Slider(
            value = length,
            onValueChange = { length = it },
            valueRange = 1f..10f,
            steps = 8
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { onStartGame(length.roundToInt()) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start Game", fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back")
        }
    }
}
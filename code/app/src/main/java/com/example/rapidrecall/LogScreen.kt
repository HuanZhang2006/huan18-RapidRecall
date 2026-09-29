package com.example.rapidrecall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * LogScreen – lists every attempt of this session, newest first.
 */
@Composable
fun LogScreen(
    attempts: List<Attempt>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Attempt Log", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))

        if (attempts.isEmpty()) {
            Text("No attempts yet. Play a game first!", modifier = Modifier.weight(1f))
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(attempts.reversed()) { attempt ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.padding(horizontal = 16.dp)) { AttemptRow(attempt) }
                    }
                }
            }
        }

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back")
        }
    }
}

@Composable
fun AttemptRow(attempt: Attempt) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = if (attempt.isCorrect) "✔ Correct" else "✘ Incorrect",
                fontWeight = FontWeight.Bold,
                color = if (attempt.isCorrect) Color(0xFF2E7D32) else Color(0xFFC62828),
                modifier = Modifier.weight(1f)
            )
            Text(formatTimestamp(attempt.timestamp), fontSize = 12.sp)
        }
        Text("Length: ${attempt.sequenceLength}")
        Text("Target: ${attempt.targetSequence}   Input: ${attempt.userInput}")
    }
}

fun formatTimestamp(timestamp: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return formatter.format(Date(timestamp))
}
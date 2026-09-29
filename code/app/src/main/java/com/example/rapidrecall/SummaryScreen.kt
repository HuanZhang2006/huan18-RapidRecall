package com.example.rapidrecall

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.text.format

/**
 * SummaryScreen – shows total attempts, correct attempts and accuracy.
 */
@Composable
fun SummaryScreen(
    summary: GameSummary,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Summary", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))

        SummaryItem("Total attempts", summary.totalAttempts.toString())
        SummaryItem("Correct attempts", summary.correctAttempts.toString())
        SummaryItem("Accuracy", "%.1f%%".format(summary.accuracy))

        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text("Back")
        }
    }
}

@Composable
fun SummaryItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Text(label, fontSize = 20.sp, modifier = Modifier.weight(1f))
        Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}
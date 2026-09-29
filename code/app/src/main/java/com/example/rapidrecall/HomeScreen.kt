package com.example.rapidrecall

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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * HomeScreen – start screen with navigation to Start, Log and Summary.
 */
@Composable
fun HomeScreen(
    onStart: () -> Unit,
    onLog: () -> Unit,
    onSummary: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text("🧠", fontSize = 48.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text("RapidRecall", fontSize = 40.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "How many digits can you remember?",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = onStart, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text("Start", fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(onClick = onLog, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text("Attempt Log", fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(onClick = onSummary, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text("Summary", fontSize = 18.sp)
        }
    }
}
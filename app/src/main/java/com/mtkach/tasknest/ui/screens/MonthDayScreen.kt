package com.mtkach.tasknest.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mtkach.tasknest.ui.components.AppScaffold
import com.mtkach.tasknest.util.dayName
import com.mtkach.tasknest.util.holidaysFor
import com.mtkach.tasknest.util.monthName

@Composable
fun MonthDayScreen(onBack: () -> Unit) {
    var monthText by remember { mutableStateOf("") }
    var dayText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    AppScaffold(title = "Місяць і день", onBack = onBack) {
        Row {
            OutlinedTextField(
                value = monthText,
                onValueChange = { monthText = it },
                label = { Text("Місяць (1-12)") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = dayText,
                onValueChange = { dayText = it },
                label = { Text("День тижня (1-7)") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }
        Button(
            onClick = {
                val month = monthText.toIntOrNull()
                val day = dayText.toIntOrNull()
                result = if (month in 1..12 && day in 1..7) {
                    val holidays = holidaysFor(month!!).joinToString("; ")
                    "${monthName(month)}, ${dayName(day!!)}. Свята: $holidays"
                } else {
                    "Місяць 1-12, день тижня 1-7"
                }
            },
            modifier = Modifier.padding(top = 12.dp)
        ) { Text("Показати") }

        if (result.isNotEmpty()) {
            Text(
                result,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

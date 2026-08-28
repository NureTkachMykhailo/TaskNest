package com.mtkach.tasknest.ui.screens

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
import com.mtkach.tasknest.util.ageLabel

@Composable
fun AgeScreen(onBack: () -> Unit) {
    var input by remember { mutableStateOf("") }
    var label by remember { mutableStateOf("") }

    AppScaffold(title = "Скільки років?", onBack = onBack) {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("Дата народження, д.М.рррр") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { label = ageLabel(input) },
            modifier = Modifier.padding(top = 12.dp)
        ) { Text("Порахувати") }

        if (label.isNotEmpty()) {
            Text(
                label,
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

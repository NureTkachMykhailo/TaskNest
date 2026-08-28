package com.mtkach.tasknest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.mtkach.tasknest.util.CalcResult
import com.mtkach.tasknest.util.Calculator

private val ops = listOf("+", "−", "×", "÷")

@Composable
fun CalculatorScreen(onBack: () -> Unit) {
    var aText by remember { mutableStateOf("") }
    var bText by remember { mutableStateOf("") }
    var history by remember { mutableStateOf(listOf<String>()) }
    var resultLine by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    fun run(op: String) {
        when (val result = Calculator.compute(aText, bText, op)) {
            is CalcResult.Ok -> {
                isError = false
                val line = "$aText $op $bText = ${result.text}"
                resultLine = line
                history = listOf(line) + history
            }
            is CalcResult.Err -> {
                isError = true
                resultLine = result.message
            }
        }
    }

    AppScaffold(title = "Калькулятор", onBack = onBack) {
        OutlinedTextField(
            value = aText,
            onValueChange = { aText = it },
            label = { Text("Перше число") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = bText,
            onValueChange = { bText = it },
            label = { Text("Друге число") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ops.forEach { op ->
                Button(onClick = { run(op) }) { Text(op) }
            }
        }
        if (resultLine.isNotEmpty()) {
            Text(
                resultLine,
                modifier = Modifier.padding(top = 12.dp),
                color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium
            )
        }
        if (history.isNotEmpty()) {
            Text("Історія", modifier = Modifier.padding(top = 16.dp), style = MaterialTheme.typography.labelLarge)
            Column {
                history.take(6).forEach { line ->
                    Text(line, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

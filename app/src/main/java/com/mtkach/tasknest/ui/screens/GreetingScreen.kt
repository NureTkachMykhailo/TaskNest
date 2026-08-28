package com.mtkach.tasknest.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mtkach.tasknest.ui.components.AppScaffold

@Composable
fun GreetingScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    AppScaffold(title = "Привітання", onBack = onBack) {
        Text(
            "Привіт! Це перший екран розминки на Jetpack Compose.",
            style = MaterialTheme.typography.bodyLarge
        )
        Button(
            onClick = {
                Toast.makeText(context, "Вітаю з першим застосунком на Kotlin!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) { Text("Показати Toast") }
    }
}

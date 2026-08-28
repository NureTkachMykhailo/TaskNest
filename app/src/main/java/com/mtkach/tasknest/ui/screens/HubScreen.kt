package com.mtkach.tasknest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mtkach.tasknest.ui.Screen
import com.mtkach.tasknest.ui.components.AppScaffold

private data class HubItem(val screen: Screen, val level: String, val subtitle: String)

private val hubItems = listOf(
    HubItem(Screen.Todo, "РІВЕНЬ 2", "Список завдань з відмітками"),
    HubItem(Screen.Calculator, "РІВЕНЬ 2", "Калькулятор на 4 операції"),
    HubItem(Screen.Greeting, "РІВЕНЬ 1", "Привітання з Toast"),
    HubItem(Screen.Age, "РІВЕНЬ 1", "Скільки років за датою народження"),
    HubItem(Screen.MonthDay, "РІВЕНЬ 1", "Місяць, день тижня та свята"),
)

@Composable
fun HubScreen(onOpen: (Screen) -> Unit) {
    AppScaffold(title = "TaskNest") {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            hubItems.forEach { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(Modifier),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    onClick = { onOpen(item.screen) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            item.level,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(item.screen.title, style = MaterialTheme.typography.titleMedium)
                        Text(item.subtitle, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

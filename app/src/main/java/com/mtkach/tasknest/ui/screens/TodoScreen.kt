package com.mtkach.tasknest.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mtkach.tasknest.data.TodoItem
import com.mtkach.tasknest.data.TodoStore
import com.mtkach.tasknest.ui.components.AppScaffold

private val seedItems = listOf(
    TodoItem(title = "Здати практичну роботу №3", done = false),
    TodoItem(title = "Повторити Jetpack Compose", done = false),
    TodoItem(title = "Купити продукти", done = true),
    TodoItem(title = "Записатися на пару з бази даних", done = false),
    TodoItem(title = "Оновити резюме", done = true),
)

@Composable
fun TodoScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val store = remember { TodoStore(context) }
    var items by remember {
        val loaded = store.load()
        mutableStateOf(loaded.ifEmpty { seedItems })
    }
    var draft by remember { mutableStateOf("") }
    var showOnlyOpen by remember { mutableStateOf(false) }

    fun persist(next: List<TodoItem>) {
        items = next
        store.save(next)
    }

    AppScaffold(title = "Список завдань", onBack = onBack) {
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            OutlinedTextField(
                value = draft,
                onValueChange = { draft = it },
                label = { Text("Нове завдання") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    val title = draft.trim()
                    if (title.isNotEmpty()) {
                        persist(listOf(TodoItem(title = title)) + items)
                        draft = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) { Text("Додати") }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                if (showOnlyOpen) "Показ: незавершені" else "Показ: усі",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(1f)
            )
            Button(onClick = { showOnlyOpen = !showOnlyOpen }) {
                Text(if (showOnlyOpen) "Показати всі" else "Тільки незавершені")
            }
        }

        val visible = if (showOnlyOpen) items.filterNot { it.done } else items

        LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
            items(visible, key = { it.id }) { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item.done,
                        onCheckedChange = { checked ->
                            persist(items.map { if (it.id == item.id) it.copy(done = checked) else it })
                        }
                    )
                    Text(item.title, modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        persist(items.filterNot { it.id == item.id })
                    }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Видалити")
                    }
                }
            }
        }
    }
}

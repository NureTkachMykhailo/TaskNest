package com.mtkach.tasknest.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mtkach.tasknest.ui.screens.AgeScreen
import com.mtkach.tasknest.ui.screens.CalculatorScreen
import com.mtkach.tasknest.ui.screens.GreetingScreen
import com.mtkach.tasknest.ui.screens.HubScreen
import com.mtkach.tasknest.ui.screens.MonthDayScreen
import com.mtkach.tasknest.ui.screens.TodoScreen

@Composable
fun TaskNestApp() {
    var screen by remember { mutableStateOf(Screen.Hub) }

    when (screen) {
        Screen.Hub -> HubScreen(onOpen = { screen = it })
        Screen.Todo -> TodoScreen(onBack = { screen = Screen.Hub })
        Screen.Calculator -> CalculatorScreen(onBack = { screen = Screen.Hub })
        Screen.Greeting -> GreetingScreen(onBack = { screen = Screen.Hub })
        Screen.Age -> AgeScreen(onBack = { screen = Screen.Hub })
        Screen.MonthDay -> MonthDayScreen(onBack = { screen = Screen.Hub })
    }
}

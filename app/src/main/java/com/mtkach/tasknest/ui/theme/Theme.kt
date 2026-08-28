package com.mtkach.tasknest.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Violet = Color(0xFF6750A4)
private val VioletContainer = Color(0xFFEADDFF)
private val Surface = Color(0xFFFBFAFF)
private val Background = Color(0xFFF4F2FA)

private val LightColors = lightColorScheme(
    primary = Violet,
    primaryContainer = VioletContainer,
    surface = Surface,
    background = Background
)

@Composable
fun TaskNestTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}

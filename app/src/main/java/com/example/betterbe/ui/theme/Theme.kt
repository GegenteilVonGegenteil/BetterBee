package com.example.betterbe.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Yellow40,
    secondary = Yellow40,
    tertiary = Yellow40,
    background = Brown80,
    surface = Brown80,
    onPrimary = Yellow60,
    onSecondary = Yellow40,
    onTertiary = Yellow40,
    onBackground = Yellow60,
    onSurface = Yellow60

)


@Composable
fun BetterBeeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
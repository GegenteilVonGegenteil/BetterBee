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
    onPrimary = Brown10,
    onSecondary = Brown10,
    onTertiary = Brown10,
    onBackground = Brown10,
    onSurface = Brown10

)


@Composable
fun BetterBeeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
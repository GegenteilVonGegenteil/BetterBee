package com.example.betterbe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.betterbe.ui.HabitApp
import com.example.betterbe.ui.theme.BetterBeeTheme

// MainActivity launches app

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BetterBeeTheme {
                HabitApp()
            }
        }
    }
}
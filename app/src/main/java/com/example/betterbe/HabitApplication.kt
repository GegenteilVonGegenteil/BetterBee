package com.example.betterbe

import android.app.Application
import com.example.betterbe.data.BetterBeeRepository
import com.example.betterbe.data.db.AppDatabase

class HabitApplication : Application() {
    val habitRepository by lazy {
        BetterBeeRepository(AppDatabase.getDatabase(this).habitDao(), AppDatabase.getDatabase(this).completionStatusDao())
    }
}
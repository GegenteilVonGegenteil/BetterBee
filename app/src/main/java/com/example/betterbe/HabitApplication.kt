package com.example.betterbe

import android.app.Application
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.data.db.AppDatabase
import com.example.betterbe.workManager.scheduleDailyCompletionStatusWorker

class HabitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        scheduleDailyCompletionStatusWorker(this)
    }
    val habitRepository by lazy {
        HabitRepository(AppDatabase.getDatabase(this).habitDao(), AppDatabase.getDatabase(this).completionStatusDao())
    }
}
package com.example.betterbe

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.data.db.AppDatabase
import com.example.betterbe.workManager.CreateCompletionStatusWorker
import com.example.betterbe.workManager.scheduleDailyCompletionStatusWorker
import com.example.betterbe.workManager.scheduleTestCompletionStatusWorker

class HabitApplication : Application() {
    lateinit var repository: HabitRepository
    override fun onCreate() {
        super.onCreate()
        repository = habitRepository
        scheduleDailyCompletionStatusWorker(this)
    }
    val habitRepository by lazy {
        HabitRepository(AppDatabase.getDatabase(this).habitDao(), AppDatabase.getDatabase(this).completionStatusDao())
    }
}
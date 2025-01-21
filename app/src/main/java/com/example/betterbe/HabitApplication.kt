package com.example.betterbe

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.data.db.AppDatabase
import com.example.betterbe.workManager.scheduleDailyCompletionStatusWorker

class HabitApplication : Application(), Configuration.Provider {
    lateinit var repository: HabitRepository
    override fun onCreate() {
        super.onCreate()
        repository = habitRepository
        scheduleDailyCompletionStatusWorker(this)
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .build()


    val habitRepository by lazy {
        HabitRepository(AppDatabase.getDatabase(this).habitDao(), AppDatabase.getDatabase(this).completionStatusDao())
    }
}
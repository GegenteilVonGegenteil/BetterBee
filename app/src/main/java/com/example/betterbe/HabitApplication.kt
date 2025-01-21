package com.example.betterbe

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.data.db.AppDatabase
import com.example.betterbe.workManager.scheduleDailyCompletionStatusWorker
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate

class HabitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        checkAndCreateCompletionStatuses()
    }

    val habitRepository by lazy {
        HabitRepository(AppDatabase.getDatabase(this).habitDao(), AppDatabase.getDatabase(this).completionStatusDao())
    }

    private fun checkAndCreateCompletionStatuses() {
        GlobalScope.launch {
            val habits = habitRepository.habits.first()
            val currentDate = LocalDate.now()
            habits.forEach { habit ->
                var date = currentDate
                var completionStatus: CompletionStatus? = null
                while (completionStatus == null) {
                    completionStatus = habitRepository.getCompletionStatusForDate(habit.id, date)
                    if (completionStatus == null) {
                        val newCompletionStatus = CompletionStatus(
                            habitId = habit.id,
                            date = date,
                            completed = false,
                            id = 0
                        )
                        habitRepository.insertCompletionStatus(newCompletionStatus)
                    }
                    date = date.minusDays(1)
                }
            }
        }
    }
}

// If WorkManager didn't kill scheduler
/*class HabitApplication : Application(), Configuration.Provider {
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
}*/
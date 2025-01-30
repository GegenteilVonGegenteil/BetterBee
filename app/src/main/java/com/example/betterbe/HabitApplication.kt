package com.example.betterbe

import android.app.Application
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.data.db.AppDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate

// checks and create completion statuses before displaying on launch

class HabitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        checkAndCreateCompletionStatuses()
    }

    val habitRepository by lazy {
        HabitRepository(AppDatabase.getDatabase(this).habitDao(), AppDatabase.getDatabase(this).completionStatusDao())
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun checkAndCreateCompletionStatuses() {
        GlobalScope.launch {
            val habits = habitRepository.habits.first()
            val currentDate = LocalDate.now()
            habits.forEach { habit ->
                val lastCompletionStatus = habitRepository.getLastCompletionStatus(habit.id)
                if(lastCompletionStatus!!.date != currentDate){
                    val startDate = lastCompletionStatus.date.plusDays(1)
                    var date = startDate

                    while (date.isBefore(currentDate) || date.isEqual(currentDate)) {
                        val newCompletionStatus = CompletionStatus(
                            habitId = habit.id,
                            date = date,
                            completed = false,
                            id = 0
                        )
                        habitRepository.insertCompletionStatus(newCompletionStatus)
                        date = date.plusDays(1)
                    }
                }
            }
        }
    }
}
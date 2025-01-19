package com.example.betterbe.workManager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.betterbe.HabitApplication
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.HabitRepository
import kotlinx.coroutines.flow.first
import java.time.LocalDate

class CreateCompletionStatusWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("CreateCompletionStatusWorker", "Worker is running")
        return try {
            val repository = (applicationContext as HabitApplication).repository // Access the repository from the application context
            val habits = repository.habits.first() // Get the list of habits
            habits.forEach { habit ->
                val completionStatus = CompletionStatus(
                    habit.id,
                    LocalDate.now(),
                    false,
                    0
                )
                repository.insertCompletionStatus(completionStatus)
            }
            Log.d("CreateCompletionStatusWorker", "Completion statuses created")
            Result.success()
        } catch (e: Exception) {
            Log.e("CreateCompletionStatusWorker", "Error creating completion statuses", e)
            Result.failure()
        }
    }
}

/* class CreateCompletionStatusWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val habitRepository: HabitRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("CreateCompletionStatusWorker", "Worker is running")
        return try {
            val habits = habitRepository.habits.first()
            habits.forEach { habit ->
                val completionStatus = CompletionStatus(
                    habit.id,
                    LocalDate.now(),
                    false,
                    0
                )
                habitRepository.insertCompletionStatus(completionStatus)
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
} */
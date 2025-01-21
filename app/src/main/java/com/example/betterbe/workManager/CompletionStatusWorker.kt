package com.example.betterbe.workManager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.betterbe.HabitApplication
import com.example.betterbe.data.CompletionStatus
import kotlinx.coroutines.flow.first
import java.time.LocalDate

class CreateCompletionStatusWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.e("Worker", "Worker Started aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        return try {
            val repository = (applicationContext as HabitApplication).repository
            val habits = repository.habits.first()
            habits.forEach { habit ->
                val completionStatus = CompletionStatus(
                    habit.id,
                    LocalDate.now(),
                    false,
                    0
                )
                repository.insertCompletionStatus(completionStatus)
            }
            Log.e("Worker", "Worker Finished aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            Result.success()
        } catch (e: Exception) {
            Log.e("Worker", "Worker Failed aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", e)
            Result.failure()
        }
    }
}
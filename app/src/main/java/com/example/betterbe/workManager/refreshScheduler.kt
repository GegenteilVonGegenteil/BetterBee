package com.example.betterbe.workManager

import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit


fun scheduleTestCompletionStatusWorker(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<CreateCompletionStatusWorker>(2, TimeUnit.MINUTES)
        .setInitialDelay(2, TimeUnit.MINUTES) // Initial delay of 5 minutes
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "CreateCompletionStatusWorker",
        ExistingPeriodicWorkPolicy.REPLACE,
        workRequest
    )
}

fun scheduleDailyCompletionStatusWorker(context: Context) {
    val workRequest = PeriodicWorkRequestBuilder<CreateCompletionStatusWorker>(1, TimeUnit.DAYS)
        .setInitialDelay(calculateInitialDelay(), TimeUnit.MILLISECONDS)
        .build()


    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "CreateCompletionStatusWorker",
        ExistingPeriodicWorkPolicy.REPLACE,
        workRequest
    )
}

fun calculateInitialDelay(): Long {
    val currentTime = Calendar.getInstance()
    val midnightTime = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 21)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        add(Calendar.DAY_OF_MONTH, 1)
    }
    return midnightTime.timeInMillis - currentTime.timeInMillis
}
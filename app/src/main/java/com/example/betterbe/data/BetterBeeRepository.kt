package com.example.betterbe.data

import com.example.betterbe.data.db.CompletionStatusDao
import com.example.betterbe.data.db.CompletionStatusEntity
import com.example.betterbe.data.db.HabitDao
import com.example.betterbe.data.db.HabitEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class BetterBeeRepository(
    private val habitDao: HabitDao,
    private val completionStatusDao: CompletionStatusDao
) {
    suspend fun addHabit(habit: HabitEntity): Long {
        return habitDao.addHabit(habit)
    }

    suspend fun updateHabit(habit: HabitEntity) {
        habitDao.update(habit)
    }

    suspend fun deleteHabit(habit: HabitEntity) {
        habitDao.delete(habit)
        completionStatusDao.deleteCompletionStatusesForHabit(habit._id)
    }

    fun getAllHabits(): Flow<List<HabitEntity>> {
        return habitDao.getAllHabits()
    }

    fun getHabitById(habitId: Int): HabitEntity {
        return habitDao.getHabitById(habitId)
    }

    suspend fun insertCompletionStatus(completionStatus: CompletionStatusEntity) {
        completionStatusDao.addCompletionStatus(completionStatus)
    }

    suspend fun changeCheckedStatus(habitId: Int) {
        val completionStatus = getLastCompletionStatus(habitId)
        if (completionStatus != null) {
            completionStatusDao.updateCompletionStatus(habitId, !completionStatus.completed)
        }
    }

    suspend fun getCompletionStatusForDate(habitId: Int, date: LocalDate): CompletionStatusEntity? {
        return completionStatusDao.getCompletionStatusForDate(habitId, date)
    }

    fun getCompletionStatusForHabit(habitId: Int): Flow<List<CompletionStatusEntity>> {
        return completionStatusDao.getCompletionStatusForHabit(habitId)
    }

    suspend fun getLastCompletionStatus(habitId: Int): CompletionStatusEntity? {
        return completionStatusDao.getCurrentCompletionStatus(habitId)
    }
}
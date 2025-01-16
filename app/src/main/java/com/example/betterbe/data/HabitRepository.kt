package com.example.betterbe.data

import com.example.betterbe.data.db.CompletionStatusDao
import com.example.betterbe.data.db.CompletionStatusEntity
import com.example.betterbe.data.db.HabitDao
import com.example.betterbe.data.db.HabitEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class HabitRepository(
    private val habitDao: HabitDao,
    private val completionStatusDao: CompletionStatusDao
) {
    suspend fun addHabit(habit: HabitEntity) : Long{
        return habitDao.addHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        //habitDao.update(HabitEntity(habit.id, habit.name, habit.color))
    }

    suspend fun deleteHabit(habit: Habit) {
        //habitDao.delete(HabitEntity(habit.id, habit.name, habit.color))
        completionStatusDao.deleteCompletionStatusesForHabit(habit.id)
    }

    fun getAllHabits(): Flow<List<HabitEntity>> {
        return habitDao.getAllHabits()
    }

    /**fun getHabitById(habitId: Int): Habit {
        val habitEntity = habitDao.getHabitById(habitId)
        return Habit(
            habitEntity._id, habitEntity.name, habitEntity.color
        )
    } username test **/

    suspend fun insertCompletionStatus(completionStatus: CompletionStatus) {
        completionStatusDao.addCompletionStatus(CompletionStatusEntity(0, completionStatus.habitId, LocalDate.now(), false))
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
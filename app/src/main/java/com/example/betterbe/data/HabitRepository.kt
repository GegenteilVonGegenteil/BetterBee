package com.example.betterbe.data

import com.example.betterbe.data.db.CompletionStatusDao
import com.example.betterbe.data.db.CompletionStatusEntity
import com.example.betterbe.data.db.HabitDao
import com.example.betterbe.data.db.HabitEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

// communication tool between app and database
// converts the Entities into Objects and vice versa

class HabitRepository(
    private val habitDao: HabitDao,
    private val completionStatusDao: CompletionStatusDao
) {
    suspend fun addHabit(habit: Habit) : Long{
        return habitDao.addHabit(HabitEntity(habit.name, habit.color))
    }

    suspend fun updateHabit(habit: Habit) {
        habitDao.update(HabitEntity(habit.name, habit.color, habit.id))
    }

    suspend fun deleteHabit(habit: Habit) {
        completionStatusDao.deleteCompletionStatusesForHabit(habit.id)
        habitDao.delete(HabitEntity(habit.name, habit.color, habit.id))
    }

    val habits = habitDao.getAllHabits().map { habitList ->
        habitList.map { habit ->
            Habit(habit.name, habit.color, habit._id)
        }
    }

    suspend fun getHabitById(habitId: Int): Habit {
        val habitEntity = habitDao.getHabitById(habitId)
        return Habit(
            habitEntity.name, habitEntity.color, habitEntity._id
        )
    }

    suspend fun insertCompletionStatus(completionStatus: CompletionStatus) {
        completionStatusDao.addCompletionStatus(CompletionStatusEntity(
            completionStatus.habitId, completionStatus.date, completionStatus.completed, completionStatus.id
            ))
    }

    suspend fun changeCheckedStatus(habitId: Int) {
        val completionStatus = getLastCompletionStatus(habitId)
        if (completionStatus != null) {
            completionStatusDao.updateCompletionStatus(completionStatus.id, !completionStatus.completed)
        }
    }

    fun getCompletionStatusesForHabit(habitId: Int): Flow<List<CompletionStatus>> {
        val completionStates = completionStatusDao.getCompletionStatusForHabit(habitId)
        return completionStates.map{ completionStatesList ->
            completionStatesList.map { completionState ->
                CompletionStatus(completionState.habitId, completionState.date, completionState.completed, completionState._id)
            }
        }
    }

    suspend fun getLastCompletionStatus(habitId: Int): CompletionStatus? {
        val completionState = completionStatusDao.getCurrentCompletionStatus(habitId)
        return completionState?.let {
            CompletionStatus(it.habitId, it.date, it.completed, it._id)
        }
    }
}
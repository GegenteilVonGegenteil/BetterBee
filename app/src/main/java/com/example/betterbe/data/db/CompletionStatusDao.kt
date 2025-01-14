package com.example.betterbe.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface CompletionStatusDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCompletionStatus(completionStatus: CompletionStatusEntity)

    @Query("UPDATE completion_status SET completed  = :completed WHERE habitId = :habitId")
    suspend fun updateCompletionStatus(habitId: Int, completed: Boolean)

    @Query("SELECT * FROM completion_status WHERE habitId = :habitId AND date = :date")
    suspend fun getCompletionStatusForDate(habitId: Int, date: LocalDate): CompletionStatusEntity?

    @Query("SELECT * FROM completion_status WHERE habitId = :habitId")
    suspend fun getCompletionStatusForHabit(habitId: Int): Flow<List<CompletionStatusEntity>>

    @Query("SELECT * FROM completion_status WHERE habitId = :habitId ORDER BY date DESC LIMIT 1")
    suspend fun getCurrentCompletionStatus(habitId: Int): CompletionStatusEntity?

    @Query("DELETE FROM completion_status WHERE habitId = :habitId")
    suspend fun deleteCompletionStatusesForHabit(habitId: Int)
}
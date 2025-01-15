package com.example.betterbe.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "completion_status")
data class CompletionStatusEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val habitId: Int,
    val date: LocalDate = LocalDate.now(),
    val completed: Boolean = false
)
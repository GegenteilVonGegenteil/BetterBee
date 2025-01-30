package com.example.betterbe.data.db

// object used in the data layer

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "completion_status")
data class CompletionStatusEntity(
    val habitId: Int,
    val date: LocalDate = LocalDate.now(),
    val completed: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
)
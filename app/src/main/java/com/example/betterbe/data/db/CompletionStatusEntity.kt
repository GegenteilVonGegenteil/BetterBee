package com.example.betterbe.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

// object used in the data layer
// CompletionStatusEntity is both the table within the room database as well as an object

@Entity(tableName = "completion_status")
data class CompletionStatusEntity(
     val habitId: Int,
    val date: LocalDate = LocalDate.now(),
    val completed: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    val _id: Int = 0,
)
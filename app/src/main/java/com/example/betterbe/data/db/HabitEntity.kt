package com.example.betterbe.data.db

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    val name: String,
    val color: String,

    @PrimaryKey(autoGenerate = true)
    val _id: Int=0,
)
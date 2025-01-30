package com.example.betterbe.data.db

// object used in the data layer
// HabitEntity is both the table within the room database as well as an object

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    val name: String,
    val color: String,

    @PrimaryKey(autoGenerate = true)
    val _id: Int=0,
)
package com.example.betterbe.data

import java.time.LocalDate

// representation for the CompletionStatusEntity within the application itself

data class CompletionStatus (
    val habitId: Int,
    val date: LocalDate,
    val completed: Boolean,
    val id: Int
)
package com.example.betterbe.data

// representation for the CompletionStatusEntity within the application itself

import java.time.LocalDate

data class CompletionStatus (
    val habitId: Int,
    val date: LocalDate,
    val completed: Boolean,
    val id: Int
)
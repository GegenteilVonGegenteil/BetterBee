package com.example.betterbe.data

import java.time.LocalDate

data class CompletionStatus (
    val habitId: Int,
    val date: LocalDate,
    val completed: Boolean,
    val id: Int
)
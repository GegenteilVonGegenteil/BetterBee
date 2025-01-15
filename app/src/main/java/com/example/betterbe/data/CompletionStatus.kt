package com.example.betterbe.data

import java.time.LocalDate

data class CompletionStatus (
    val id: Int,
    val habitId: Int,
    val date: LocalDate,
    val completed: Boolean
)
package com.example.betterbe.ui.detail

// has functions for getting completion statuses for habits & deleting habits

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.Habit
import com.example.betterbe.data.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HabitDetailUIState(
    val habit: Habit
)

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val habitRepository: HabitRepository
): ViewModel() {
    private val habitId: Int = savedStateHandle["habitId"] ?: 0

    private val _habitDetailUIState = MutableStateFlow(
        HabitDetailUIState(
            Habit("", "", 0)
        )
    )

    val habitDetailUIState = _habitDetailUIState.asStateFlow()

    fun loadHabit() {
        viewModelScope.launch {
            val habit = habitRepository.getHabitById(habitId)
            _habitDetailUIState.update {
                it.copy(habit = habit)
            }
        }
    }

    fun getCompletionStatusesForHabit(habitId: Int): Flow<List<CompletionStatus>> {
       return habitRepository.getCompletionStatusesForHabit(habitId)
    }

    fun deleteHabitItem(it: Habit) {
        viewModelScope.launch {
            habitRepository.deleteHabit(it)
        }
    }
}
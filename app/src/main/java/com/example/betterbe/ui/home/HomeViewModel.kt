package com.example.betterbe.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.Habit
import com.example.betterbe.data.HabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// gets the habits for the home page list & sorts them in descending order

class HomeViewModel(private val repository: HabitRepository) : ViewModel() {

    private val _habitsWithCompletionStatus = MutableStateFlow<List<Pair<Habit, CompletionStatus?>>>(emptyList())
    val habitsWithCompletionStatus: StateFlow<List<Pair<Habit, CompletionStatus?>>>
        get() = _habitsWithCompletionStatus

    init {
        loadHabits()
    }

    private fun loadHabits() {
        viewModelScope.launch {
            repository.habits.collect { habits ->
                val habitsWithStatus = habits.map { habit ->
                    habit to repository.getLastCompletionStatus(habit.id)
                }.sortedByDescending { it.second?.completed == false }
                _habitsWithCompletionStatus.value = habitsWithStatus
            }
        }
    }

    fun changeCompletionStatus(habit: Habit) {
        viewModelScope.launch {
            repository.changeCheckedStatus(habit.id)
            loadHabits()
        }
    }

    fun getCompletedHabitsCount(habitsWithStatus: List<Pair<Habit, CompletionStatus?>>): Int {
        return habitsWithStatus.count { it.second?.completed == true }
    }
}
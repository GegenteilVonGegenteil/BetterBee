package com.example.betterbe.ui.manage

// habits are gotten from repository as a state if user is trying to access them
// before habits are gotten, it's an empty list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betterbe.data.Habit
import com.example.betterbe.data.HabitRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ManageViewModel(private val repository: HabitRepository) : ViewModel() {

    val habits = repository.habits.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )


    fun deleteHabitItem(it: Habit) {
        viewModelScope.launch {
            repository.deleteHabit(it)
        }
    }
}
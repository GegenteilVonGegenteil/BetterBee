package com.example.betterbe.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.data.db.CompletionStatusEntity
import com.example.betterbe.data.db.HabitEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HabitRepository) : ViewModel() {

    private val _habitsWithCompletionStatus = MutableStateFlow<List<Pair<HabitEntity, CompletionStatusEntity?>>>(emptyList())
    val habitsWithCompletionStatus: StateFlow<List<Pair<HabitEntity, CompletionStatusEntity?>>>
        get() = _habitsWithCompletionStatus

    init {
        loadHabits()
    }

    private fun loadHabits() {
        viewModelScope.launch {
            repository.getAllHabits().collect { habits ->
                val habitsWithStatus = habits.map { habit ->
                    habit to repository.getLastCompletionStatus(habit._id)
                }.sortedByDescending { it.second?.completed == false }
                _habitsWithCompletionStatus.value = habitsWithStatus
            }
        }
    }

    fun changeCompletionStatus(habit: HabitEntity) {
        viewModelScope.launch {
            repository.changeCheckedStatus(habit._id)
            loadHabits()
        }
    }

    fun getCompletedHabitsCount(habitsWithStatus: List<Pair<HabitEntity, CompletionStatusEntity?>>): Int {
        return habitsWithStatus.count { it.second?.completed == true }
    }
}
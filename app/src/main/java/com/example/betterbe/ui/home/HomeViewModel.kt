package com.example.betterbe.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.betterbe.data.Habit
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.data.db.CompletionStatusEntity
import com.example.betterbe.data.db.HabitEntity
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HabitRepository) : ViewModel() {
    fun changeCompletionStatus(habit: HabitEntity) {
        viewModelScope.launch {
            repository.changeCheckedStatus(habit._id)
        }
    }

    val habitsWithCompletionStatus: LiveData<List<Pair<HabitEntity, CompletionStatusEntity?>>> = repository.getAllHabits()
        .map { habits ->
            habits.map { habit ->
                habit to repository.getLastCompletionStatus(habit._id)
            }
        }
        .asLiveData()

    fun getCompletedHabitsCount(habitsWithStatus: List<Pair<HabitEntity, CompletionStatusEntity?>>): Int {
        return habitsWithStatus.count { it.second?.completed == true }
    }
}
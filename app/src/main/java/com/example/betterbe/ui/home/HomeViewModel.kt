package com.example.betterbe.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.data.db.CompletionStatusEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HabitRepository) : ViewModel() {
   suspend fun getCompletionStatus(habitId :Int): CompletionStatusEntity? {
       return repository.getLastCompletionStatus(habitId)
   }

    fun changeCompletionStatus(habitId: Int) {
        viewModelScope.launch {
            repository.changeCheckedStatus(habitId)
        }
    }

    val toDoUIState = repository.getAllHabits().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
}
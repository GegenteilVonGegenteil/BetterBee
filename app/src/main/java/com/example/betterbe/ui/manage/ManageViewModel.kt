package com.example.betterbe.ui.manage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.data.db.HabitEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ManageViewModel(private val repository: HabitRepository) : ViewModel() {

    val habits = repository.getAllHabits().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )


    fun deleteHabitItem(it: HabitEntity) {
        viewModelScope.launch {
            repository.deleteHabit(it)
        }
    }
}
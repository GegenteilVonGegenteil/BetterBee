package com.example.betterbe.ui.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.Habit
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.ui.detail.HabitDetailUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class EditHabitViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: HabitRepository
) : ViewModel() {
    private val habitId: Int = savedStateHandle["habitId"] ?: 0

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _color = MutableStateFlow("yellow")
    val color: StateFlow<String> = _color


    private val _isValid = mutableStateOf(true)
    val isValid: State<Boolean> = _isValid

    init {
        viewModelScope.launch {
            val habit = repository.getHabitById(habitId)
            _name.value = habit.name
            _color.value = habit.color
        }
    }

    fun onNameChange(newName: String) {
        _name.value = newName
        _isValid.value = newName.isNotBlank()
    }

    fun onColorChange(newColor: String) {
        _color.value = newColor
    }

    fun updateHabit(onHabitUpdated: () -> Unit) {
        if(_name.value.isBlank()) {
            _isValid.value = false
        } else {
            _isValid.value = true
            viewModelScope.launch {
                val updatedHabit = Habit(_name.value ?: "", _color.value ?: "", habitId)
                repository.updateHabit(updatedHabit)
                onHabitUpdated()
            }
        }
    }
}
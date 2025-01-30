package com.example.betterbe.ui.edit

// uses state and prefills the habit info on the edit page, then updates with new info

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betterbe.data.Habit
import com.example.betterbe.data.HabitRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditHabitViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: HabitRepository
) : ViewModel() {
    val habitId: Int = savedStateHandle["habitId"] ?: 0

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
                val updatedHabit = Habit(_name.value, _color.value, habitId)
                repository.updateHabit(updatedHabit)
                onHabitUpdated()
            }
        }
    }
}
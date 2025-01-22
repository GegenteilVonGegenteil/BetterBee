package com.example.betterbe.ui.add

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.Habit
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.data.db.CompletionStatusEntity
import com.example.betterbe.data.db.HabitEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddHabitViewModel(
    private val repository: HabitRepository
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _color = MutableStateFlow("yellow")
    val color: StateFlow<String> = _color

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onColorChange(newColor: String) {
        _color.value = newColor
    }

    fun addHabit(name: String, color:String, onHabitAdded: () -> Unit) {
        viewModelScope.launch {

            val newHabit = repository.addHabit(Habit(name, color))
            repository.insertCompletionStatus(
                CompletionStatus(
                newHabit.toInt(),
                LocalDate.now(),
                false,
                0
                )
            )
        }

        onHabitAdded()
    }
}
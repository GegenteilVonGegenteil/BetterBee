package com.example.betterbe.ui.add

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.Habit
import com.example.betterbe.data.HabitRepository
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

    // isValid = whether any text is there, just to prevent empty fields
    private val _isValid = mutableStateOf(true)
    val isValid: State<Boolean> = _isValid

    // called whenever Textfield value changes
    fun onNameChange(newName: String) {
        _name.value = newName
        _isValid.value = newName.isNotBlank()
    }

    // called whenever Textfield value changes
    fun onColorChange(newColor: String) {
        _color.value = newColor
    }

    // check for valid field (not empty), then if valid goes through with creating the habit
    fun addHabit(name: String, color:String, onHabitAdded: () -> Unit) {
        if(_name.value.isBlank()) {
            _isValid.value = false
        } else {
            _isValid.value = true
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
        }
        onHabitAdded()
    }
}
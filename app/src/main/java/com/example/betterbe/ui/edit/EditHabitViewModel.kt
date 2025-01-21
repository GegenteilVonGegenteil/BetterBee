package com.example.betterbe.ui.edit

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
import kotlinx.coroutines.launch
import java.time.LocalDate

class EditHabitViewModel(
    private val repository: HabitRepository
) : ViewModel() {
    //Works the same way useState() works in Next.js

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _color = MutableLiveData("yellow")
    val color: LiveData<String> = _color

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onColorChange(newColor: String) {
        _color.value = newColor
    }

    fun editHabit(name: String, color:String, onHabitEdited: () -> Unit) {
        viewModelScope.launch {

            val newHabit = repository.editHabit(Habit(name, color))
            repository.insertCompletionStatus(
                CompletionStatus(
                    newHabit.toInt(),
                    LocalDate.now(),
                    false,
                    0
                )
            )
        }

        onHabitEdited()
    }
}

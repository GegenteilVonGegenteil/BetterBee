package com.example.betterbe.ui.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.Habit
import com.example.betterbe.data.HabitRepository
import com.example.betterbe.data.db.HabitEntity
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddHabitViewModel(
    private val repository: HabitRepository
) : ViewModel() {
    //Works the same way useState() works in Next.js

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    //color
    // private val _description = MutableLiveData("")
    // val description: LiveData<String> = _description

    fun onTitleChange(newTitle: String) {
        _name.value = newTitle
    }

    // fun color change

    fun addHabit(name: String, onHabitAdded: () -> Unit) {
        viewModelScope.launch {

            val newHabit = repository.addHabit(HabitEntity(name, "red"))
            repository.insertCompletionStatus(CompletionStatus(0, newHabit.toInt(), LocalDate.now(), false))
        }

        onHabitAdded()
    }
}
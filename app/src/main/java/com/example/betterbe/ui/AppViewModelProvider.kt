package com.example.betterbe.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.betterbe.HabitApplication
import com.example.betterbe.ui.home.HomeViewModel
import com.example.betterbe.ui.manage.ManageViewModel

object AppViewModelProvider {

    val Factory = viewModelFactory {

        initializer {
            val habitApplication = this[APPLICATION_KEY] as HabitApplication
            HomeViewModel(
                habitApplication.habitRepository
            )
        }

        initializer {
            val habitApplication = this[APPLICATION_KEY] as HabitApplication
            ManageViewModel(
                habitApplication.habitRepository
            )
        }

    }
}
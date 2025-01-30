package com.example.betterbe.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.betterbe.HabitApplication
import com.example.betterbe.ui.add.AddHabitViewModel
import com.example.betterbe.ui.detail.DetailViewModel
import com.example.betterbe.ui.edit.EditHabitViewModel
import com.example.betterbe.ui.home.HomeViewModel
import com.example.betterbe.ui.manage.ManageViewModel

// initialises all the viewModels and gives them the repository

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

        initializer {
            val habitApplication = this[APPLICATION_KEY] as HabitApplication
            DetailViewModel(
                this.createSavedStateHandle(),
                habitRepository = habitApplication.habitRepository
            )
        }

        initializer {
            val habitApplication = this[APPLICATION_KEY] as HabitApplication
            AddHabitViewModel(
                habitApplication.habitRepository
            )
        }

        initializer {
            val habitApplication = this[APPLICATION_KEY] as HabitApplication
            EditHabitViewModel(
                this.createSavedStateHandle(),
                habitApplication.habitRepository
            )
        }

    }
}
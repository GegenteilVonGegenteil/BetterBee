package com.example.betterbe.ui.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.betterbe.ui.AppViewModelProvider
import java.lang.reflect.Modifier

@Composable
fun HomeView(
    modifier: Modifier,
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {}
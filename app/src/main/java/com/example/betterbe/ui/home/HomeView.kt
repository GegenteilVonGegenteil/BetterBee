package com.example.betterbe.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.betterbe.R
import com.example.betterbe.ui.AppViewModelProvider
import com.example.betterbe.ui.components.HabitListItem


@Composable
fun HomeView(
    modifier: Modifier,
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onHabitClick: (Int) -> Unit
) {

    val habitsWithStatus by homeViewModel.habitsWithCompletionStatus.collectAsStateWithLifecycle()
    val completedCount = homeViewModel.getCompletedHabitsCount(habitsWithStatus)
    val totalCount = habitsWithStatus.size


    Column(modifier = Modifier.fillMaxSize()){

        Text(
            "Today",
            style = TextStyle(
                fontSize = 31.sp,
                fontWeight = FontWeight(700),
                color = colorResource(R.color.bronco_50)
            ),
            modifier = Modifier.padding(start = 20.dp, top = 30.dp, bottom = 5.dp)
        )
        Text(
            "($completedCount/$totalCount habits done)",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.bronco_50)
                ),
            modifier = Modifier.padding(start = 20.dp, top = 5.dp, bottom = 30.dp)
        )
        LazyColumn {
            itemsIndexed(habitsWithStatus) { index, (habit, completionStatus) ->
                Log.e("habits", habit.toString())
                HabitListItem(
                    habit,
                    completionStatus,
                    modifier,
                    onCardClick = {onHabitClick(habit._id)},
                    onCheckClick = {homeViewModel.changeCompletionStatus(it)}
                )
            }
        }
    }
}
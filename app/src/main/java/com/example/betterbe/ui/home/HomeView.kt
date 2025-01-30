package com.example.betterbe.ui.home

// Home page view, has the habit onClick for completion/undo
// gets the habits with their completionStatus in pairs (val habitsWithStatus)

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.example.betterbe.ui.theme.Jost


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
                fontFamily = Jost,
                fontSize = 31.sp,
                fontWeight = FontWeight(700),
                color = colorResource(R.color.bronco_50)
            ),
            modifier = Modifier.padding(start = 20.dp, top = 30.dp, bottom = 5.dp)
        )
        Text(
            "($completedCount/$totalCount habits done)",
            style = TextStyle(
                fontFamily = Jost,
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                color = colorResource(R.color.bronco_50)
                ),
            modifier = Modifier.padding(start = 20.dp, top = 5.dp, bottom = 30.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth()
        ) {
            itemsIndexed(habitsWithStatus) { _: Int, (habit, completionStatus) ->
                HabitListItem(
                    habit,
                    completionStatus,
                    modifier,
                    onCardClick = { onHabitClick(habit.id) },
                    onCheckClick = { homeViewModel.changeCompletionStatus(it) }
                )
            }
        }
    }
}
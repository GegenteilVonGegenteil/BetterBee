package com.example.betterbe.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.betterbe.R
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.db.HabitEntity
import com.example.betterbe.ui.AppViewModelProvider
import com.example.betterbe.ui.components.HabitListItem
import java.time.LocalDate


@Composable
fun HomeView(
    modifier: Modifier,
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Column(modifier = Modifier.fillMaxSize()){

        Text(
            "Today",
            style = TextStyle(
                fontSize = 31.sp,
                //fontFamily = FontFamily(Font(R.font.jost)),
                fontWeight = FontWeight(700),
                color = colorResource(R.color.bronco_50)
            ),
            modifier = Modifier.padding(start = 20.dp, top = 30.dp, bottom = 5.dp)
        )
        Text(
            "(2/5 habits)",
            style = TextStyle(
                fontSize = 20.sp,
                //fontFamily = FontFamily(Font(R.font.jost)),
                fontWeight = FontWeight(400),
                color = colorResource(R.color.bronco_50)
                ),
            modifier = Modifier.padding(start = 20.dp, top = 5.dp, bottom = 30.dp)
        )
        LazyColumn {
        }
    }
}
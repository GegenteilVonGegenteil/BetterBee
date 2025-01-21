package com.example.betterbe.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.betterbe.R
import com.example.betterbe.ui.AppViewModelProvider
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DetailView(
    navController: NavController,
    detailViewModel: DetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val state by detailViewModel.habitDetailUIState.collectAsStateWithLifecycle()
    val completionStatuses by detailViewModel.getCompletionStatusesForHabit(state.habit.id).collectAsStateWithLifecycle(emptyList())
    val markedDates = completionStatuses.filter { it.completed }.map { it.date }

    Column (Modifier.padding(24.dp)) {
        Text(state.habit.name,
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 31.sp,
                fontWeight = FontWeight(700),
                color = colorResource(R.color.bronco_50)
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ){
            CalendarView(
                markedDates = markedDates
            )
        }

    }
}



@Composable
fun CalendarView(markedDates: List<LocalDate>) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
    val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library
    val daysOfWeek = daysOfWeek()

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    Column {
        Text(
            text = state.firstVisibleMonth.yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                color = colorResource(R.color.bronco_50)
            ),
            modifier = Modifier.padding(20.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            for (dayOfWeek in daysOfWeek) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                )
            }
        }

        HorizontalCalendar(
            state = state,
            dayContent = { day ->
                Day(day, markedDates)
            },
        )
    }
}

@Composable
fun Day(day: CalendarDay, markedDates: List<LocalDate>) {
    val isMarked = day.date in markedDates
    val isInMonth = day.position == DayPosition.MonthDate

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .background(
                color = if (isMarked) colorResource(R.color.bronco_50) else Color.Transparent,
                shape = if (isMarked) CircleShape else RectangleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = when {
                isMarked && isInMonth -> colorResource(R.color.bronco_950)
                isInMonth -> colorResource(R.color.bronco_50)
                else -> colorResource(R.color.bronco_700)
            }
        )
    }
}
package com.example.betterbe.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.currentStateAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.betterbe.R
import com.example.betterbe.data.Habit
import com.example.betterbe.ui.AppViewModelProvider
import com.example.betterbe.ui.theme.Jost
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

// overview for specific habit, with edit and delete buttons
// completion rate out of days since habit was created & month overview

@Composable
fun DetailView(
    navController: NavController,
    detailViewModel: DetailViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    // the habit that is currently displayed
    val state by detailViewModel.habitDetailUIState.collectAsStateWithLifecycle()
    // all completion Statuses for the habit
    val completionStatuses by detailViewModel.getCompletionStatusesForHabit(state.habit.id).collectAsStateWithLifecycle(emptyList())
    // all dates for which a Completion Status exists (used for styling in the calender)
    val existingDates = completionStatuses.map {it.date}
    // dates on which the habits was done (for styling and info)
    val markedDates = completionStatuses.filter { it.completed }.map { it.date }
    // if the popup for deletion is shown
    var showDialog by remember { mutableStateOf(false) }

    //  whenever the detail page is the current page in the Stack, the habit is fetched form the VM
    // this means we can popBack after editing with the detail page showing the changes
    val navigationState by navController.currentBackStackEntry!!.lifecycle.currentStateAsState()

    LaunchedEffect(navigationState) {
        if(navigationState == Lifecycle.State.RESUMED)
        {
            detailViewModel.loadHabit()
        }
    }

    // sets the habitColor depending on the current habits color
    val habitColor = when (state.habit.color) {
        "red" -> colorResource(R.color.red_light)
        "orange" -> colorResource(R.color.orange_light)
        "yellow" -> colorResource(R.color.yellow_light)
        "green" -> colorResource(R.color.green_light)
        "blue" -> colorResource(R.color.blue_light)
        "violet" -> colorResource(R.color.violet_light)
        else -> {
            colorResource(R.color.yellow_light)
        }
    }

    // Shows delete popup by setting the variable to true
    fun onDeleteClick(habit: Habit) {
        showDialog = true
    }

    // Button to go back
    FloatingActionButton(
        onClick = {  navController.popBackStack() },
        modifier = Modifier
            .padding(20.dp)
            .size(40.dp),
        containerColor = colorResource(R.color.bronco_50),
        contentColor = colorResource(R.color.bronco_950)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "Back"
        )
    }

    Column (Modifier.padding( 24.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Column {
                Text(state.habit.name,
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = Jost,
                        fontSize = 31.sp,
                        fontWeight = FontWeight(700),
                        color = colorResource(R.color.bronco_50)
                    )
                )
                Text(
                    "completed ${markedDates.size} of ${completionStatuses.size} days",
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Row {
                IconButton(
                    modifier = Modifier.padding(2.dp),
                    onClick = {navController.navigate("edit/${state.habit.id}")}
                ) {
                    Icon(
                        imageVector =Icons.Outlined.Edit,
                        contentDescription = "Edit Habit",
                        tint = habitColor,
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(
                    modifier = Modifier.padding(2.dp),
                    onClick = { onDeleteClick(state.habit)}
                ) {
                    Icon(
                        imageVector =Icons.Filled.DeleteForever,
                        contentDescription = "Delete Habit",
                        tint = habitColor,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }

        Box(

            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){

            CalendarView(
                markedDates = markedDates,
                habitColor,
                existingDates
            )
        }

    }

    // popup for when delete is pressed
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Delete this habit for all eternity?") },
            text = { Text("This cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        detailViewModel.deleteHabitItem(state.habit)
                        navController.navigate("home")
                    }
                ) {
                    Text("Delete it")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }
}

// integrated calendar for overview of habit completions
// uses kizitonwoses kalender component

@Composable
fun CalendarView(markedDates: List<LocalDate>, habitColor: Color, existingDates: List<LocalDate>) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
    val daysOfWeek = daysOfWeek()

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    Column {
        Text(
            // shows the currently displayed month
            text = state.firstVisibleMonth.yearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            style = androidx.compose.ui.text.TextStyle(
                fontFamily = Jost,
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
                color = colorResource(R.color.bronco_50)
            ),
            modifier = Modifier.padding(20.dp)
        )

        // Row for the Weekdays
        Row(modifier = Modifier.fillMaxWidth()) {
            for (dayOfWeek in daysOfWeek) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = Jost,
                        color = habitColor
                    )
                )
            }
        }

        // the Calendar Component by kizitonwose, Day() component below
        HorizontalCalendar(
            state = state,
            dayContent = { day ->
                Day(day, markedDates, habitColor, existingDates)
            },
        )
    }
}

@Composable
fun Day(day: CalendarDay, markedDates: List<LocalDate>, habitColor: Color, existingDates: List<LocalDate>) {
    // if the day is one where the habit was done
    // styled by a filled circle
    val isMarked = day.date in markedDates

    // if the Day is in the month currently displayed
    // if day isn't in month, the number is desaturated
    val isInMonth = day.position == DayPosition.MonthDate

    // the date on which the habit was created
    val earliestDate = existingDates.minOrNull() ?: LocalDate.now()

    // days before the habit was created
    // if the habit didn't exist, the number is desaturated
    val isBeforeEarliestDate = day.date.isBefore(earliestDate)

    // today
    // current day marked by empty circle
    val isToday = day.date.isEqual(LocalDate.now())

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .background(
                color = if (isMarked) habitColor else Color.Transparent,
                shape = if (isMarked) CircleShape else RectangleShape
            )
            .border(
                width = 2.dp,
                color = if (isToday) colorResource(R.color.bronco_50) else Color.Transparent,
                shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = when {
                isMarked -> colorResource(R.color.bronco_950)
                isInMonth && !isBeforeEarliestDate  -> colorResource(R.color.bronco_50)
                else -> colorResource(R.color.bronco_700)
            }
        )
    }
}
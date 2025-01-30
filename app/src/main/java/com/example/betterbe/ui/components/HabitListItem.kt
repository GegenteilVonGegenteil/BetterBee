package com.example.betterbe.ui.components

// styling/setup for each habit on home page, with completionStatus (done or not) changing the colours

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betterbe.R
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.Habit
import com.example.betterbe.ui.theme.Jost

@Composable
fun HabitListItem(
    habit: Habit,
    completionStatus: CompletionStatus?,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit,
    onCheckClick: (Habit) -> Unit
) {
    val habitColor = when (habit.color) {
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

    Card(
        onClick = {onCardClick()},
        colors = CardDefaults.cardColors(
            containerColor = if(completionStatus?.completed == true) colorResource(R.color.bronco_900) else habitColor
        ),
        modifier = modifier
            .padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(345.dp)
                .padding(start = 8.dp, top = 12.dp, end = 8.dp, bottom = 12.dp)

        ) {
            Text(
                text = habit.name,
                style = TextStyle(
                    fontFamily = Jost,
                    fontSize = 25.sp,
                    fontWeight = FontWeight(400),
                    color = if (completionStatus?.completed == true) habitColor else colorResource(R.color.bronco_950),
                    textDecoration = if (completionStatus?.completed == true) TextDecoration.LineThrough else TextDecoration.None
                    ),
                modifier = Modifier.weight(3f)
            )
            IconButton(
                modifier = Modifier.padding(2.dp),
                onClick = { onCheckClick(habit) }
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = if (completionStatus?.completed == false) Icons.Default.CheckBoxOutlineBlank else Icons.Outlined.CheckBox,
                    contentDescription = "CheckBox",
                    tint = if (completionStatus?.completed == true) habitColor else colorResource(R.color.bronco_950)
                )
            }
        }
    }
}
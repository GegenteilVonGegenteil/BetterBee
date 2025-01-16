package com.example.betterbe.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.CheckCircle
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
import com.example.betterbe.data.Habit
import com.example.betterbe.data.db.CompletionStatusEntity
import com.example.betterbe.data.db.HabitEntity

@Composable
fun HabitListItem(
    habit: Habit,
    completionStatus: CompletionStatusEntity?,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit,
    onCheckClick: (Habit) -> Unit
) {
    val habitColor = when (habit.color) {
        "red" -> colorResource(R.color.red_light)
        "orange" -> colorResource(R.color.orange_light)
        "yellow" -> colorResource(R.color.orange_light)
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
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(345.dp)
                .height(80.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)

        ) {
            Text(
                text = "Drink Water",
                style = TextStyle(
                    fontSize = 25.sp,
                    //fontFamily = FontFamily(Font(R.font.jost)),
                    fontWeight = FontWeight(400),
                    color = if (completionStatus?.completed == true) colorResource(R.color.bronco_100) else colorResource(R.color.bronco_950),
                    textDecoration = if (completionStatus?.completed == true) TextDecoration.LineThrough else TextDecoration.None
                    )
            )
            IconButton(
                modifier = Modifier.padding(2.dp),
                onClick = { onCheckClick(habit) }
            ) {
                Icon(
                    imageVector = if (completionStatus?.completed == true) Icons.Default.CheckBoxOutlineBlank else Icons.Outlined.CheckBox,
                    contentDescription = "CheckBox",
                    tint = if (completionStatus?.completed == true) colorResource(R.color.bronco_100) else colorResource(R.color.bronco_950)
                )
            }
        }
    }
}
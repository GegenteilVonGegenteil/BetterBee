package com.example.betterbe.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.betterbe.R
import com.example.betterbe.data.CompletionStatus
import com.example.betterbe.data.db.HabitEntity
import java.time.LocalDate

@Composable
fun HabitListItem(
    habit: HabitEntity,
    completionStatus: CompletionStatus,
    modifier: Modifier = Modifier
) {
    val habitColor = when (habit.color) {
        "red_light" -> colorResource(R.color.red_light)
        "orange_light" -> colorResource(R.color.orange_light)
        "yellow_light" -> colorResource(R.color.orange_light)
        "green_light" -> colorResource(R.color.green_light)
        "blue_light" -> colorResource(R.color.blue_light)
        "violet_light" -> colorResource(R.color.violet_light)
        else -> {
            colorResource(R.color.yellow_light)
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = if(completionStatus.completed) colorResource(R.color.bronco_900) else habitColor
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
                    color = if (completionStatus.completed) colorResource(R.color.bronco_100) else colorResource(R.color.bronco_950),
                    textDecoration = if (completionStatus.completed) TextDecoration.LineThrough else TextDecoration.None
                    )
            )
            IconButton(
                modifier = Modifier.padding(2.dp),
                onClick = {  }
            ) {
                Icon(
                    imageVector =  Icons.Default.CheckCircle,
                    contentDescription = "CheckBox",
                    tint = if (completionStatus.completed) colorResource(R.color.bronco_100) else colorResource(R.color.bronco_950)
                )
            }
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    HabitListItem(habit = HabitEntity(0, "name", "red_light"), completionStatus = CompletionStatus(0,0, LocalDate.now(), true), modifier = Modifier)
}
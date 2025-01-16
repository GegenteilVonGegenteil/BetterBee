package com.example.betterbe.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.outlined.Edit
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.betterbe.R
import com.example.betterbe.data.Habit

@Composable
fun ManageListItem(
    modifier: Modifier,
    habit: Habit,
    onEditClick: () -> Unit,
    onDeleteClick: (Habit) -> Unit
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
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.bronco_900)
        ),
        modifier = modifier
            .padding(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(345.dp)
                .padding(start = 16.dp, top = 22.dp, end = 16.dp, bottom = 22.dp)

        ) {
            Text(
                text = habit.name,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight(400),
                    color = habitColor
                ),
                modifier = Modifier.weight(3f)
            )
            Row {
                IconButton(
                    modifier = Modifier.padding(2.dp),
                    onClick = {onEditClick()}
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
                    onClick = { onDeleteClick(habit)}
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
    }
}
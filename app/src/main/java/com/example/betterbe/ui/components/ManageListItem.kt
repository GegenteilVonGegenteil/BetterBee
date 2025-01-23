package com.example.betterbe.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.betterbe.ui.manage.ManageViewModel
import com.example.betterbe.ui.theme.Jost

@Composable
fun ManageListItem(
    modifier: Modifier,
    habit: Habit,
    navController: NavController,
    onEditClick: () -> Unit,
    manageViewModel: ManageViewModel
) {
    var showDialog by remember { mutableStateOf(false) }

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
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.bronco_900)
        ),
        modifier = modifier
            .padding(10.dp),
        onClick = {navController.navigate("detail/${habit.id}")}
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
                    onClick = { showDialog = true}
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
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Delete this habit for all eternity?") },
            text = { Text("This cannot be undone.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        manageViewModel.deleteHabitItem(habit)
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


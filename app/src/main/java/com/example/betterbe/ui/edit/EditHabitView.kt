package com.example.betterbe.ui.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Hexagon
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.betterbe.R
import com.example.betterbe.ui.AppViewModelProvider
import com.example.betterbe.ui.theme.Jost

// Edit habit view, with popups when user tries to leave without saved changes (showDialog)
// same as AddHabitView, but gets previous habit information (the state name and colour are prefilled by the viewModel)

@Composable
fun EditHabitView(
    navController: NavHostController,
    editHabitViewModel: EditHabitViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val name by editHabitViewModel.name.collectAsStateWithLifecycle("")
    val color by editHabitViewModel.color.collectAsStateWithLifecycle("")

    // if field is empty
    val isValid by editHabitViewModel.isValid

    // if popUp should be shown before canceling
    var showDialog by remember { mutableStateOf(false) }

    FloatingActionButton(
        onClick = {  showDialog = true },
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

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Edit Habit",
            style = TextStyle(
                color = Color.White,
                fontSize = 36.sp,
                fontFamily = Jost,
                fontWeight = FontWeight.W800
            ),
            modifier = Modifier
                .padding(top = 48.dp, bottom = 56.dp)
                .align(Alignment.CenterHorizontally)

        )

        TextField(
            value = name,
            onValueChange = { editHabitViewModel.onNameChange(it) },
            label = { Text("Name") },
            isError = !isValid,
            modifier = Modifier
                .width(380.dp)
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .align(Alignment.CenterHorizontally)
        )
        if(!isValid) {
            Text(
                text = "Field cannot be empty",
                color = colorResource(R.color.red_light),
                modifier = Modifier.padding(horizontal = 30.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        )
        {
            IconButton(
                onClick = { editHabitViewModel.onColorChange("red") },
                modifier =
                if (color == "red")
                    Modifier.background(colorResource(R.color.bronco_900), shape = CircleShape)
                else Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Hexagon,
                    contentDescription = "Red",
                    tint = colorResource(R.color.red_light),
                    modifier =
                    if (color == "red")
                        Modifier.size(56.dp) else Modifier.size(32.dp)
                )
            }

            IconButton(
                onClick = { editHabitViewModel.onColorChange("orange") },
                modifier =
                if (color == "orange")
                    Modifier.background(colorResource(R.color.bronco_900), shape = CircleShape)
                else Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Hexagon,
                    contentDescription = "Orange",
                    tint = colorResource(R.color.orange_light),
                    modifier =
                    if (color == "orange")
                        Modifier.size(56.dp) else Modifier.size(32.dp)
                )
            }

            IconButton(
                onClick = { editHabitViewModel.onColorChange("yellow") },
                modifier =
                if (color == "yellow")
                    Modifier.background(colorResource(R.color.bronco_900), shape = CircleShape)
                else Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Hexagon,
                    contentDescription = "Yellow",
                    tint = colorResource(R.color.yellow_light),
                    modifier =
                    if (color == "yellow")
                        Modifier.size(56.dp) else Modifier.size(32.dp)
                )
            }

            IconButton(
                onClick = { editHabitViewModel.onColorChange("green") },
                modifier =
                if (color == "green")
                    Modifier.background(colorResource(R.color.bronco_900), shape = CircleShape)
                else Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Hexagon,
                    contentDescription = "Green",
                    tint = colorResource(R.color.green_light),
                    modifier =
                    if (color == "green")
                        Modifier.size(56.dp) else Modifier.size(32.dp)
                )
            }

            IconButton(
                onClick = { editHabitViewModel.onColorChange("blue") },
                modifier =
                if (color == "blue")
                    Modifier.background(colorResource(R.color.bronco_900), shape = CircleShape)
                else Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Hexagon,
                    contentDescription = "Blue",
                    tint = colorResource(R.color.blue_light),
                    modifier =
                    if (color == "blue")
                        Modifier.size(56.dp) else Modifier.size(32.dp)
                )
            }

            IconButton(
                onClick = { editHabitViewModel.onColorChange("violet") },
                modifier =
                if (color == "violet")
                    Modifier.background(colorResource(R.color.bronco_900), shape = CircleShape)
                else Modifier
            ) {
                Icon(
                    imageVector = Icons.Filled.Hexagon,
                    contentDescription = "Violet",
                    tint = colorResource(R.color.violet_light),
                    modifier =
                    if (color == "violet")
                        Modifier.size(56.dp) else Modifier.size(32.dp)
                )
            }

        }


        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        )
        {

            Button(
                onClick = {
                    showDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.red_melon),
                    contentColor = Color.Black
                )
            ) {
                Text("Cancel")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    editHabitViewModel.updateHabit {
                       if(isValid) navController.popBackStack()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.green_tea),
                    contentColor = Color.Black
                )
            ) {
                Text("Save")
            }
        }
    }

    // popup for when user tries to cancel or leave page
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Are you sure?") },
            text = { Text("Any unsaved progress will be lost.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        navController.popBackStack()
                    }
                ) {
                    Text("Yes")
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
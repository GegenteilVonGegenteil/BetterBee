package com.example.betterbe.ui.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hexagon
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Hexagon
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.betterbe.R
import com.example.betterbe.ui.AppViewModelProvider
import com.example.betterbe.ui.Routes

@Composable
fun AddHabitView(
    navController: NavHostController,
    addHabitViewModel: AddHabitViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val name by addHabitViewModel.name.observeAsState("")
    val color by addHabitViewModel.color.observeAsState("yellow")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Unspecified)
    ) {
        Text(
            text = "Add Habit",
            style = TextStyle(
                color = Color.White,
                fontSize = 36.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W800
            ),
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )

        TextField(
            value = name,
            onValueChange = { addHabitViewModel.onNameChange(it) },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp).background(Color.White)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            IconButton(
                onClick = { addHabitViewModel.onColorChange("red") }
            ) {
                Icon(
                    imageVector = Icons.Filled.Hexagon,
                    contentDescription = "Red",
                    tint = colorResource(R.color.red_light),
                    modifier = Modifier.size(32.dp)
                )
            }

            IconButton(
                onClick = { addHabitViewModel.onColorChange("orange") }
            ) {
                Icon(
                    imageVector = Icons.Filled.Hexagon,
                    contentDescription = "Orange",
                    tint = colorResource(R.color.orange_light),
                    modifier = Modifier.size(32.dp)
                )
            }

        }


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { addHabitViewModel.addHabit(name.toString(), color.toString()) {
                navController.navigate(Routes.Home.route)
            } },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Save")
        }

        Button(
            onClick = { addHabitViewModel.addHabit(name.toString(), color.toString()) {
                navController.navigate(Routes.Home.route)
            } },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cancel")
        }
    }
}
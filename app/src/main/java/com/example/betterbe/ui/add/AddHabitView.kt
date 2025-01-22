package com.example.betterbe.ui.add

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Hexagon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    val name by addHabitViewModel.name.collectAsStateWithLifecycle("")
    val color by addHabitViewModel.color.collectAsStateWithLifecycle("yellow")
    val isValid by addHabitViewModel.isValid

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

    Column(
        modifier = Modifier
            .fillMaxSize()
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
                .padding(top = 48.dp, bottom = 56.dp)
                .align(Alignment.CenterHorizontally)

        )

        TextField(
            value = name,
            onValueChange = { addHabitViewModel.onNameChange(it) },
            label = { Text("Name") },
            isError = !isValid,
            modifier = Modifier
                .width(380.dp)
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .background(Color.White)
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
                onClick = { addHabitViewModel.onColorChange("red") },
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
                onClick = { addHabitViewModel.onColorChange("orange") },
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
                onClick = { addHabitViewModel.onColorChange("yellow") },
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
                onClick = { addHabitViewModel.onColorChange("green") },
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
                onClick = { addHabitViewModel.onColorChange("blue") },
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
                onClick = { addHabitViewModel.onColorChange("violet") },
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
                        navController.navigate(Routes.Home.route)
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
                    addHabitViewModel.addHabit(name.toString(), color.toString()) {
                        if(isValid) {
                        navController.navigate(Routes.Home.route)
                    }
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
}
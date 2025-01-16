package com.example.betterbe.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAddCheck
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlaylistAddCheck
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.BorderColor
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.betterbe.R
import com.example.betterbe.ui.Routes

@Composable
fun BottomNavBar(navController: NavController) {

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: Routes.Home.route
    val currentScreen = Routes.entries.firstOrNull { it.route == currentRoute }

    NavigationBar(
        containerColor = colorResource(R.color.mustard_400)
    ) {
        NavigationBarItem(
            selected = currentScreen == Routes.Home,
            onClick = { navController.navigate(Routes.Home.route) },
            icon = {
                Icon(imageVector = Icons.AutoMirrored.Filled.PlaylistAddCheck, contentDescription = "Home")
            },
            label = { Text("Home") },
            colors = NavigationBarItemColors(
                selectedIndicatorColor = colorResource(R.color.mustard_100),
                selectedIconColor = colorResource(R.color.mustard_950),
                selectedTextColor = colorResource(R.color.mustard_950),
                unselectedIconColor = colorResource(R.color.mustard_950),
                unselectedTextColor = colorResource(R.color.mustard_950),
                disabledIconColor = colorResource(R.color.bronco_900),
                disabledTextColor = colorResource(R.color.bronco_900)
            )
        )
        NavigationBarItem(
            selected = currentScreen == Routes.Add,
            onClick = { navController.navigate(Routes.Add.route) },
            icon = {
                Icon(imageVector = Icons.Outlined.AddBox, contentDescription = "Add ToDo")
            },
            label = { Text("Add") },
            colors = NavigationBarItemColors(
                selectedIndicatorColor = colorResource(R.color.mustard_100),
                selectedIconColor = colorResource(R.color.mustard_950),
                selectedTextColor = colorResource(R.color.mustard_950),
                unselectedIconColor = colorResource(R.color.mustard_950),
                unselectedTextColor = colorResource(R.color.mustard_950),
                disabledIconColor = colorResource(R.color.bronco_900),
                disabledTextColor = colorResource(R.color.bronco_900)
            )
        )
        NavigationBarItem(
            selected = currentScreen == Routes.Manager,
            onClick = { navController.navigate(Routes.Manager.route) },
            icon = {
                Icon(imageVector = Icons.Outlined.BorderColor, contentDescription = "Add ToDo")
            },
            label = { Text("Manage") },
            colors = NavigationBarItemColors(
                selectedIndicatorColor = colorResource(R.color.mustard_100),
                selectedIconColor = colorResource(R.color.mustard_950),
                selectedTextColor = colorResource(R.color.mustard_950),
                unselectedIconColor = colorResource(R.color.mustard_950),
                unselectedTextColor = colorResource(R.color.mustard_950),
                disabledIconColor = colorResource(R.color.bronco_900),
                disabledTextColor = colorResource(R.color.bronco_900)
            )
        )
    }
}
package com.example.betterbe.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.betterbe.ui.Routes

@Composable
fun BottomNavBar(navController: NavController) {

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: Routes.Home.route
    val currentScreen = Routes.entries.firstOrNull { it.route == currentRoute }

    NavigationBar {
        NavigationBarItem(
            selected = currentScreen == Routes.Home,
            onClick = { navController.navigate(Routes.Home.route) },
            icon = {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
            }, label = { Text("Home") })
        NavigationBarItem(
            selected = currentScreen == Routes.Add,
            onClick = { navController.navigate(Routes.Add.route) },
            icon = {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add ToDo")
            }, label = { Text("Add ToDo") })
        NavigationBarItem(
            selected = currentScreen == Routes.Manager,
            onClick = { navController.navigate(Routes.Manager.route) },
            icon = {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add ToDo")
            }, label = { Text("Add ToDo") })
    }
}
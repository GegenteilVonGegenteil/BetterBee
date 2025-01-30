package com.example.betterbe.ui

// the App itself, defines all routes and what is shown depending on route

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.betterbe.ui.add.AddHabitView
import com.example.betterbe.ui.components.BottomNavBar
import com.example.betterbe.ui.detail.DetailView
import com.example.betterbe.ui.edit.EditHabitView
import com.example.betterbe.ui.home.HomeView
import com.example.betterbe.ui.manage.ManageView

enum class Routes(val route: String) {
    Home("home"),
    Manager("manager"),
    Add("add"),
    Edit("edit/{habitId}"),
    Detail("detail/{habitId}")
}

@Composable
fun HabitApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val routesWithNavBar = listOf(Routes.Home.route, Routes.Manager.route)

    Scaffold(
        modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute in routesWithNavBar) {
            BottomNavBar(navController)
        }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.Home.route
        ) {
            composable(Routes.Home.route) {
                Column(Modifier.padding(innerPadding)) {
                    HomeView(Modifier){ habitId ->
                        navController.navigate("detail/$habitId")
                    }
                }
            }
            composable(Routes.Manager.route) {
                Column(Modifier.padding(innerPadding)) {
                    ManageView(
                        Modifier,
                        navController = navController
                    ) { habitId ->
                        navController.navigate("edit/$habitId")
                    }
                }
            }
            composable(Routes.Add.route) {
                Column(Modifier.padding(innerPadding)) {
                    AddHabitView(navController)
                    }
                }

            composable(
                route = Routes.Detail.route,
                arguments = listOf(navArgument("habitId") { type = NavType.IntType })
            ) {
                Column(Modifier.padding(innerPadding)) {
                    DetailView(navController)
                }
            }
            composable(
                route = Routes.Edit.route,
                arguments = listOf(navArgument("habitId") { type = NavType.IntType })
            ) {
                Column(Modifier.padding(innerPadding)) {
                    EditHabitView( navController)
                }
            }
        }
    }
}
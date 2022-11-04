package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.ProgressScreen
import com.example.myapplication.ui.screens.RoutinesScreen
import com.example.myapplication.ui.screens.SearchScreen

@Composable

fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Progress.route
    ) {
        composable(Screen.Routines.route) {
            RoutinesScreen()
        }
        composable(Screen.Progress.route) {
            ProgressScreen()
        }
        composable(Screen.Search.route) {
            SearchScreen()
        }
        composable(Screen.QR.route) {
            ProgressScreen()
        }
    }
}
package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.ExploreScreen
import com.example.myapplication.ui.screens.ProgressScreen
import com.example.myapplication.ui.screens.RoutinesScreen
import com.example.myapplication.viewmodel.DeltaViewModel

@Composable

fun NavGraph(navController: NavHostController, viewModel: DeltaViewModel ) {
    NavHost(
        navController = navController,
        startDestination = Screen.Progress.route
    ) {
        composable(Screen.Routines.route) {
            RoutinesScreen(viewModel = viewModel)
        }
        composable(Screen.Progress.route) {
            ProgressScreen(viewModel = viewModel)
        }
        composable(Screen.Explore.route) {
            ExploreScreen(viewModel = viewModel)
        }
        composable(Screen.QR.route) {
            ProgressScreen(viewModel = viewModel)
        }
    }
}
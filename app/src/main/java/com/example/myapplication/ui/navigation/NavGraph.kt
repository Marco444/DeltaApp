package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.ExploreScreen
import com.example.myapplication.ui.screens.ProgressScreen
import com.example.myapplication.ui.screens.QRScreen
import com.example.myapplication.ui.screens.RoutinesScreen
import com.example.myapplication.viewmodel.RoutinesViewModel

@Composable

/*
* La clave del disenio de navegacion es que cada pantalla tenga una accion principal a la
* que va a llevar, y de esa manera hacer que esa accion se manje direction directamente y
* unicamente desde aca
* */
fun NavGraph(navController: NavHostController, viewModel: RoutinesViewModel ) {
    NavHost(
        navController = navController,
        startDestination = NavBarScreen.Progress.route
    ) {
        composable(NavBarScreen.Routines.route) {
            RoutinesScreen(viewModel = viewModel, actionRedirect = { navController.navigate(Screen.Execute.route) })
        }
        composable(NavBarScreen.Progress.route) {
            ProgressScreen(viewModel = viewModel)
        }
        composable(NavBarScreen.Explore.route) {
            ExploreScreen(viewModel = viewModel)
        }
        composable(NavBarScreen.QR.route) {
            QRScreen(viewModel = viewModel)
        }
        composable(Screen.Execute.route) {
            ///new activity??
        }
    }
}
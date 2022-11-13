package com.example.myapplication.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.*
import com.example.myapplication.ui.viewmodel.RoutinesViewModel

@Composable

/*
* La clave del disenio de navegacion es que cada pantalla tenga una accion principal a la
* que va a llevar, y de esa manera hacer que esa accion se manje direction directamente y
* unicamente desde aca
* */
fun NavGraph(navController: NavHostController, viewModel: RoutinesViewModel, executeRedirect: (Int) -> Unit, scaffoldState: ScaffoldState) {
    NavHost(
        navController = navController,
        startDestination = NavBarScreen.Routines.route,
    ) {
        composable(NavBarScreen.Routines.route) {
            RoutinesScreen(viewModel = viewModel,
                actionRedirect = executeRedirect, scaffoldState)
        }
        composable(NavBarScreen.Progress.route) {
            ProgressScreen(viewModel = viewModel,
            actionRedirect = {navController.navigate(Screen.ProgressDetail.route + it  )}, scaffoldState)
        }
        composable(NavBarScreen.Explore.route) {
            ExploreScreen(viewModel = viewModel, scaffoldState)
        }
        composable(NavBarScreen.QR.route) {
            QRScreen(viewModel = viewModel)
        }
        composable(Screen.ProgressDetail.route) {backStackEntry ->
            ProgressDetailScreen(viewModel = viewModel,
                                 viewRoutineHandler = {navController.navigate(Screen.Execute.route)},
                backButtonHandler = {navController.popBackStack()},
                                 routineId = backStackEntry.arguments?.getString("routineId") )
        }

    }
}
package com.example.myapplication.ui.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.LogIn
import com.example.myapplication.ui.screens.*
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
        startDestination = Screen.Landing.route
    ) {
        composable(NavBarScreen.Routines.route) {
            RoutinesScreen(viewModel = viewModel,
                actionRedirect = { navController.navigate(Screen.Execute.route) })
        }
        composable(NavBarScreen.Progress.route) {
            ProgressScreen(viewModel = viewModel,
            actionRedirect = {navController.navigate(Screen.ProgressDetail.route + it  )})
        }
        composable(NavBarScreen.Explore.route) {
            ExploreScreen(viewModel = viewModel)
        }
        composable(NavBarScreen.QR.route) {
            QRScreen(viewModel = viewModel)
        }
        composable(Screen.Login.route) {
            LogIn(viewModel = viewModel, actionRedirect = {viewModel.login("", "");
                navController.navigate(NavBarScreen.Routines.route)},
                backButton = {navController.navigate(Screen.Landing.route)})
        }
        composable(Screen.Landing.route) {
            LandingScreen(loginHandler = {navController.navigate(Screen.Login.route)},
                            tryOutHandler = {navController.navigate(NavBarScreen.Routines.route)})
        }
        composable(Screen.ProgressDetail.route) {backStackEntry ->
            ProgressDetailScreen(viewModel = viewModel,
                                 viewRoutineHandler = {navController.navigate(Screen.Execute.route)},
                backButtonHandler = {navController.navigate(NavBarScreen.Progress.route)},
                                 routineId = backStackEntry.arguments?.getString("routineId") )
        }

    }
}
package com.example.myapplication.ui.navigation

import android.content.Intent
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.myapplication.ui.activities.mainactivity.UserViewModel
import com.example.myapplication.ui.screens.*
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel
import com.example.myapplication.ui.components.DrawerContent

@Composable

/*
* La clave del disenio de navegacion es que cada pantalla tenga una accion principal a la
* que va a llevar, y de esa manera hacer que esa accion se manje direction directamente y
* unicamente desde aca
* */
fun NavGraph(userViewModel: UserViewModel,
             navController: NavHostController,
             viewModel: RoutinesViewModel,
             executeRedirect: (Int) -> Unit,
             scaffoldState: ScaffoldState,
             logoutRedirect: () -> Unit) {

    NavHost(
        navController = navController,
        startDestination = NavBarScreen.Routines.route,
    ) {
        composable(NavBarScreen.Routines.route) {
            RoutinesScreen(viewModel = viewModel,
                actionRedirect = executeRedirect,
                scaffoldState = scaffoldState,
                errorRedirect = {navController.navigate(Screen.Error.route)},
                settingsRedirect = {navController.navigate(Screen.Settings.route)}
            )
        }
        composable(NavBarScreen.Progress.route) {
            ProgressScreen(viewModel = viewModel,
                actionRedirect = {viewModel.getRoutine(it);navController.navigate(Screen.ProgressDetail.route + it  )},
                scaffoldState = scaffoldState,
                errorRedirect = {navController.navigate(Screen.Error.route)},
                settingsRedirect = {navController.navigate(Screen.Settings.route)}
            )
        }
        composable(NavBarScreen.Profile.route) {
            DrawerContent(userViewModel = userViewModel, logoutRedirect = logoutRedirect)
        }
        composable(NavBarScreen.Explore.route) { entry ->
            ExploreScreen(viewModel = viewModel,
                scaffoldState = scaffoldState,
                actionRedirect = executeRedirect,
                refferedRoutineId = id,
                errorRedirect = {navController.navigate(Screen.Error.route)},
                settingsRedirect = {navController.navigate(Screen.Settings.route)}
            )
        }
        composable(Screen.Error.route) {
            ApiErrorScreen()
        }
        composable(Screen.ProgressDetail.route) {backStackEntry ->
            ProgressDetailScreen(
                viewModel = viewModel,
                viewRoutineHandler = {navController.navigate(Screen.Execute.route)},
                backButtonHandler = {navController.popBackStack()},
                routineId = backStackEntry.arguments?.getString("routineId"),
                errorRedirect = {navController.navigate(Screen.Error.route)})
        }
        composable(Screen.Settings.route){
            SettingsPage(viewModel = viewModel, backHandler = {navController.popBackStack()})
        }

    }
}
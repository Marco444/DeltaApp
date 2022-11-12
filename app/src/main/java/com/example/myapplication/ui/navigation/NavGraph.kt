package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.LogIn
import com.example.myapplication.ui.screens.*
import com.example.myapplication.viewmodel.ExecuteRoutineViewModel
import com.example.myapplication.viewmodel.RoutinesState
import com.example.myapplication.viewmodel.RoutinesViewModel

@Composable

/*
* La clave del disenio de navegacion es que cada pantalla tenga una accion principal a la
* que va a llevar, y de esa manera hacer que esa accion se manje direction directamente y
* unicamente desde aca
* */
fun NavGraph(navController: NavHostController, viewModel: RoutinesViewModel, executeRedirect: () -> Unit) {
    NavHost(
        navController = navController,
        startDestination = NavBarScreen.Routines.route,
    ) {
        composable(NavBarScreen.Routines.route) {
            RoutinesScreen(viewModel = viewModel,
                actionRedirect = { navController.navigate(Screen.RoutineDescriptionScreen.route + it) })
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
        composable(Screen.RoutineDescriptionScreen.route) {backStackEntry ->
            RoutineDescriptionScreen(viewModel = viewModel, backStackEntry.arguments?.getString("routineId"),starRoutineHanlder = executeRedirect,backHandler = {  navController.popBackStack() })
        }
        composable(Screen.Execute.route){ backStackEntry ->
            ExerciseExecScreen( order = 0, routineId = backStackEntry.arguments?.getString("routineId"), handlerBack = {navController.popBackStack()},handlerFinishRoutine = {navController.navigate(Screen.ProgressDetail.route + backStackEntry.arguments?.getString("routineId")?.substringAfter('}')?.toInt())})
        }
        composable(Screen.ProgressDetail.route) {backStackEntry ->
            ProgressDetailScreen(viewModel = viewModel,
                                 viewRoutineHandler = {navController.navigate(Screen.Execute.route)},
                backButtonHandler = {navController.popBackStack()},
                                 routineId = backStackEntry.arguments?.getString("routineId") )
        }

    }
}
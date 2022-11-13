package com.example.myapplication.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.ui.screens.ExerciseExecScreen
import com.example.myapplication.ui.screens.RoutineDescriptionScreen
import com.example.myapplication.ui.screens.RoutineFinished
import com.example.myapplication.ui.viewmodel.ExecuteRoutineViewModel

class DeltaAppExecute {
}

@Composable
fun DeltaAppExecute(
    navController: NavHostController = rememberNavController(),
    saved: String,
    redirectHandler: () -> Unit,
    viewModel: ExecuteRoutineViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.RoutineDescriptionScreen.route,
    ) {
        composable(Screen.RoutineDescriptionScreen.route) {
            RoutineDescriptionScreen(
                viewModel =viewModel,
                routineId = saved,
                backHandler = redirectHandler,
                starRoutineHanlder = { navController.navigate(Screen.Execute.route) })
        }
        composable(Screen.Execute.route) { backStackEntry ->
            ExerciseExecScreen(
                viewModel =  viewModel,
                handlerBack = { navController.popBackStack() },
                handlerFinishRoutine = {
                    navController.navigate(
                        Screen.RoutineFinish.route)
                })
        }
        composable(Screen.RoutineFinish.route){
            RoutineFinished(viewModel = viewModel, viewRoutineHandler = {}, routineId ="",nextHandler = redirectHandler, backButtonHandler = {})
        }
    }
}
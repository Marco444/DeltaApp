package com.example.myapplication.ui.activities.thirdactivity

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.ui.screens.*

class DeltaAppExecute {
}

@Composable
fun DeltaAppExecute(
    navController: NavHostController = rememberNavController(),
    routineId: String,
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
                routineId = routineId,
                backHandler = { redirectHandler().also { navController.popBackStack() } },
                starRoutineHanlder = { navController.navigate(Screen.Execute.route) },
                starRoutineLiteHandler = {navController.navigate(Screen.ExecuteLite.route)},
                errorRedirect = {navController.navigate(Screen.Error.route)},
                liteMode = false
            )
        }
        composable(Screen.Execute.route) { backStackEntry ->
            ExerciseExecScreen(
                viewModel =  viewModel,
                handlerBack = { navController.popBackStack() },
                handlerFinishRoutine = {
                    navController.navigate(
                        Screen.RoutineFinish.route)
                },
                errorRedirect = {navController.navigate(Screen.Error.route)}
            )
        }
        composable(Screen.ExecuteLite.route) { backStackEntry ->
            ExerciseExecuteScreenAlternative(
                viewModel =  viewModel,
                handlerBack = { navController.popBackStack() },
                handlerFinishRoutine = {
                    navController.navigate(
                        Screen.RoutineFinish.route)
                },
                errorRedirect = {navController.navigate(Screen.Error.route)}
            )
        }
        composable(Screen.RoutineFinish.route){
            RoutineFinished(viewModel = viewModel,
                viewRoutineHandler = {},
                routineId = routineId,
                nextHandler = redirectHandler,
                backButtonHandler = {},
                errorRedirect = {navController.navigate(Screen.Error.route)}
            )
        }
        composable(Screen.Error.route) {
            ApiErrorScreen()
        }
    }
}
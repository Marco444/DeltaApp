package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.ui.screens.ExerciseExecScreen
import com.example.myapplication.ui.screens.RoutineDescriptionScreen

class DeltaAppExecute {
}

@Composable
fun DeltaAppExecute(
    navController: NavHostController = rememberNavController(),
    saved: String,
    redirectHandler: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.RoutineDescriptionScreen.route
    ) {
        composable(Screen.RoutineDescriptionScreen.route) {
            RoutineDescriptionScreen(
                routineId = saved,
                backHandler = redirectHandler,
                starRoutineHanlder = { navController.navigate(Screen.Execute.route) })
        }
        composable(Screen.Execute.route) { backStackEntry ->
            ExerciseExecScreen(
                order = 0,
                routineId = backStackEntry.arguments?.getString("routineId"),
                handlerBack = { navController.popBackStack() },
                handlerFinishRoutine = {
                    navController.navigate(
                        Screen.ProgressDetail.route + backStackEntry.arguments?.getString(
                            "routineId"
                        )?.substringAfter('}')?.toInt()
                    )
                })
        }
    }
}
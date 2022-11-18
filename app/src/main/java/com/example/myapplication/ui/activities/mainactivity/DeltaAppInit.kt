package com.example.myapplication.ui.activities.mainactivity

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import com.example.myapplication.ui.screens.LogIn
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.util.getViewModelFactory

@Composable
fun DeltaAppInit(
    viewModel: UserViewModel = viewModel(factory = getViewModelFactory()),
    navController: NavHostController,
    initialisedHandler: (Int) -> Unit,
) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "http://deltapp.com/{id}"
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )) { entry ->
            val id = entry.arguments?.getInt("id") ?: -1
            LogIn(
                actionRedirect = { initialisedHandler(it); navController.popBackStack() },
                viewModel = viewModel,
                routineId = id
            )
        }
    }
}
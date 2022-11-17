package com.example.myapplication.ui.activities.mainactivity

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import com.example.myapplication.ui.screens.LogIn
import com.example.myapplication.ui.screens.LandingScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.util.getViewModelFactory

@Composable
fun DeltaAppInit(
    viewModel: UserViewModel = viewModel(factory = getViewModelFactory()),
    navController: NavHostController,
    initialisedHandler: () -> Unit,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            LandingScreen(
                loginHandler = { navController.navigate(Screen.Login.route) },
                tryOutHandler = initialisedHandler,
                userViewModel = viewModel,
            )
        }
        composable(Screen.Login.route,) {
            LogIn(actionRedirect = initialisedHandler,
                backButton = {navController.popBackStack()},
                viewModel = viewModel )
        }
    }
}
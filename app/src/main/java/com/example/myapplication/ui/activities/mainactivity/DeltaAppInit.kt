package com.example.myapplication.ui.activities.mainactivity

import androidx.compose.runtime.Composable
import com.example.myapplication.ui.screens.LogIn
import com.example.myapplication.ui.screens.LandingScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.util.getViewModelFactory

@Composable
fun DeltaAppInit(
    viewModel: UserViewModel = viewModel(factory = getViewModelFactory()),
    navController: NavHostController = rememberNavController(),
    initialisedHandler: () -> Unit,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            LandingScreen(
                loginHandler = { navController.navigate(Screen.Login.route) },
                tryOutHandler = initialisedHandler
            )
        }
        composable(Screen.Login.route,
//            deepLinks = listOf(
//                navDeepLink {
//                    uriPattern = "http://test.com"
//                }
//            )
        ) {
            LogIn(actionRedirect = initialisedHandler,
                backButton = {navController.popBackStack()},
                viewModel = viewModel )
        }
    }
}
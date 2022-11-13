package com.example.myapplication.ui.activities.mainactivity

import androidx.compose.runtime.Composable
import com.example.myapplication.ui.screens.LogIn
import com.example.myapplication.ui.screens.LandingScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun DeltaAppInit(
    viewModel: UserViewModel = viewModel(),
    //mainViewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    initialisedHandler: () -> Unit,
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            LandingScreen(
                loginHandler = { navController.navigate("login2") },
                tryOutHandler = initialisedHandler)
        }
        composable("login2",
//            deepLinks = listOf(
//                navDeepLink {
//                    uriPattern = "http://test.com"
//                }
//            )
        ) {
            LogIn(actionRedirect = initialisedHandler,
                backButton = {navController.navigate("home")} )
        }
    }
}
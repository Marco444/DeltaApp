package com.example.myapplication

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.myapplication.LogIn
import com.example.myapplication.ui.screens.LandingScreen
import com.example.myapplication.viewmodel.UserViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink

@Composable
fun DeltaAppInit(
    viewModel: UserViewModel = viewModel(),
    initialisedHandler: () -> Unit,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            LandingScreen(
                loginHandler = { navController.navigate("login") },
                tryOutHandler = initialisedHandler)
        }
        composable("login",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "http://pl-coding.com"
                }
            )) {
            LogIn(viewModel = viewModel, actionRedirect = initialisedHandler,
                 backButton = {navController.navigate("home")} )
        }
    }
}
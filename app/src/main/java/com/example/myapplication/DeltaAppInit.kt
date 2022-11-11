package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.myapplication.LogIn
import com.example.myapplication.ui.screens.LandingScreen
import com.example.myapplication.viewmodel.UserViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DeltaAppInit(
    viewModel: UserViewModel = viewModel(),
    initialisedHandler: () -> Unit,
) {

    //podria hacerse un NavHost y NavGraph pero no nos parecio la pena para
    //unicamente dos pantallas muy simples
    var loginScreen by rememberSaveable { mutableStateOf(false) }

    if(!loginScreen) {
        LandingScreen(
            loginHandler = { loginScreen = true },
            tryOutHandler = initialisedHandler)
    } else {
        LogIn(viewModel = viewModel, actionRedirect = initialisedHandler) { loginScreen = false }
    }

}
package com.example.myapplication

import android.graphics.Color
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.BottomBar
import com.example.myapplication.ui.navigation.NavGraph
import com.example.myapplication.viewmodel.RoutinesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.theme.BackgroundButton
import com.example.myapplication.ui.theme.Black
import com.example.myapplication.ui.theme.Green
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DeltaApp(
    modifier: Modifier = Modifier,
    viewModel: RoutinesViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Green
        )
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        NavGraph(navController = navController, viewModel = viewModel)
    }
}

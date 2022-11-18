package com.example.myapplication.ui.activities.secondactivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.BottomBar
import com.example.myapplication.ui.navigation.NavGraph
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.activities.mainactivity.UserViewModel
import com.example.myapplication.ui.components.DrawerContent
import com.example.myapplication.ui.navigation.SideBar
import com.example.myapplication.util.getRoutineViewModelFactory
import com.example.myapplication.util.getViewModelFactory


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DeltaApp(
    activity: SecondActivity,
    modifier: Modifier = Modifier,
    viewModel: RoutinesViewModel = viewModel(factory = getRoutineViewModelFactory()),
    navController: NavHostController = rememberNavController(),
    executeRedirect: (Int) -> Unit,
    logoutRedirect: () -> Unit,
    userViewModel: UserViewModel = viewModel(factory = getViewModelFactory()),
) {
    val windowSize = calculateWindowSizeClass(activity).widthSizeClass
    viewModel.setWidth(windowSize)

    // Variables for drawer
    val scaffoldState = rememberScaffoldState()

    if (WindowWidthSizeClass.Compact == windowSize) {
        Scaffold(
            modifier = modifier,
            bottomBar = {
                BottomBar(navController = navController)
            },
            drawerContent = {
                DrawerContent(
                    userViewModel,
                    logoutRedirect = {userViewModel.logout().invokeOnCompletion {  logoutRedirect()}})
            },
            scaffoldState = scaffoldState
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = it.calculateBottomPadding(),
                    )
            ) {
                NavGraph(
                    userViewModel = userViewModel,
                    logoutRedirect = logoutRedirect,
                    navController = navController,
                    viewModel = viewModel,
                    executeRedirect = executeRedirect,
                    scaffoldState = scaffoldState,
                )
            }
        }
    } else {
        Scaffold(
            modifier = modifier,
            bottomBar = {
                SideBar(navController = navController)
            },
            scaffoldState = scaffoldState
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = it.calculateBottomPadding() / 10,
                    )
            ) {
                NavGraph(
                    userViewModel = userViewModel,
                    logoutRedirect = logoutRedirect,
                    navController = navController,
                    viewModel = viewModel,
                    scaffoldState = scaffoldState,
                    executeRedirect = executeRedirect,
                )
            }
        }
    }
}



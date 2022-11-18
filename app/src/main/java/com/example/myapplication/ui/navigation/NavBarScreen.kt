package com.example.myapplication.ui.navigation

import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.*


sealed class Screen(val route: String) {
    object Error: Screen("error_screen")
    object ExecuteLite: Screen("execute_lite_screen/{routineId}")
    object Execute: Screen( "execute_screen/{routineId}")
    object RoutineDescriptionScreen: Screen( "routine_description_screen/{routineId}")
    object ProgressDetail: Screen( "progress_detail_screen/{routineId}")
    object RoutineFinish : Screen("routineFinish")
    object Login : Screen("login")
    object Home : Screen("home")
    object Settings : Screen("settings")
}

sealed class NavBarScreen(val title: String, val icon: ImageVector, private val route_: String): Screen(route = route_) {
    object Routines: NavBarScreen("Routines", Icons.Default.FitnessCenter , route_ = "routines_screen")
    object Progress: NavBarScreen("Progress", Icons.Default.BarChart, route_ = "progress_screen")
    object Explore: NavBarScreen("Explore", Icons.Default.Search, route_ = "explore_screen")
    object QR: NavBarScreen("QR", Icons.Default.QrCode, route_ = "qr_screen")
}


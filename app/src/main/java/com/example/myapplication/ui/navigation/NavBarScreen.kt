package com.example.myapplication.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.R


sealed class Screen(val route: String) {
    object Execute: Screen( "execute_screen/{routineId}")
    object RoutineDescriptionScreen: Screen( "routine_description_screen/{routineId}")
    object ProgressDetail: Screen( "progress_detail_screen/{routineId}")
}

sealed class NavBarScreen(val title: String, val icon: ImageVector, private val route_: String): Screen(route = route_) {
    object Routines: NavBarScreen("Routines", Icons.Default.FitnessCenter ,"routines_screen")
    object Progress: NavBarScreen("Progress", Icons.Default.BarChart, "progress_screen")
    object Explore: NavBarScreen("Explore", Icons.Default.Search, "explore_screen")
    object QR: NavBarScreen("QR", Icons.Default.QrCode, "qr_screen")

}


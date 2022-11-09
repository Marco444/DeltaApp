package com.example.myapplication.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route: String) {
    object Execute: Screen( "execute_screen")
    object Login: Screen( "login")
    object Landing: Screen( "landing")
}

sealed class NavBarScreen(val title: String, val icon: ImageVector, private val route_: String): Screen(route = route_) {
    object Routines: NavBarScreen("Routines", Icons.Filled.Phone, "routines_screen")
    object Progress: NavBarScreen("Progress", Icons.Filled.Home, "progress_screen")
    object Explore: NavBarScreen("Explore", Icons.Filled.Search, "explore_screen")
    object QR: NavBarScreen("QR", Icons.Filled.Lock, "qr_screen")

}


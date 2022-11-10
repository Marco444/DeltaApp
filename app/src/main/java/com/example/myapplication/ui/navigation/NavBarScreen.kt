package com.example.myapplication.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.R


sealed class Screen(val route: String) {
    object Execute: Screen( "execute_screen")
    object RoutineDescriptionScreen: Screen( "routine_description_screen/{routineId}")
    object ProgressDetail: Screen( "progress_detail_screen/{routineId}")
    object Login: Screen( "login")
    object Landing: Screen( "landing")
}

sealed class NavBarScreen(val title: String, val icon: Int, private val route_: String): Screen(route = route_) {
    object Routines: NavBarScreen("Routines", R.drawable.fitness_center_fill0_wght400_grad0_opsz48, "routines_screen")
    object Progress: NavBarScreen("Progress", R.drawable.bar_chart_fill0_wght400_grad0_opsz48, "progress_screen")
    object Explore: NavBarScreen("Explore", R.drawable.add_white_24dp, "explore_screen")
    object QR: NavBarScreen("QR", R.drawable.qr_code_2_fill0_wght400_grad0_opsz48, "qr_screen")

}


package com.example.myapplication.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val title: String, val icon: ImageVector, val route: String) {
    object Routines: Screen("Routines", Icons.Filled.Phone, "routines_screen")
    object Progress: Screen("Progress", Icons.Filled.Home, "progress_screen")
    object Explore: Screen("Explore", Icons.Filled.Search, "explore_screen")
    object QR: Screen("QR", Icons.Filled.Lock, "qr_screen")
}
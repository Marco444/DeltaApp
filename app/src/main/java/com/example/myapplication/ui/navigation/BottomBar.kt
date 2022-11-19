package com.example.myapplication.ui.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Gray
import com.example.myapplication.ui.theme.Green


@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        NavBarScreen.Routines,
        NavBarScreen.Progress,
        NavBarScreen.Explore,
    )

    BottomNavigation (backgroundColor = Gray, contentColor = Green) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = {
                    Text(
                        text = if(item == NavBarScreen.Explore) stringResource(id = R.string.explore_title)
                            else if(item == NavBarScreen.Progress) stringResource(id = R.string.progress_title)
                                else stringResource(id = R.string.routines_title),
                        color = Green
                    )
                    },
                alwaysShowLabel = false,
                unselectedContentColor = Color.Gray,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
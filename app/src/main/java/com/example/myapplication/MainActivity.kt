package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.myapplication.ui.screens.LandingScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = androidx.compose.ui.graphics.Color.Black )

                val navigate = Intent(this@MainActivity, SecondActivity::class.java)

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        LandingScreen(
                            loginHandler = { navController.navigate("login") },
                            tryOutHandler = {startActivity(navigate)})
                    }
                    composable("login",
//                        deepLinks = listOf(
//                            navDeepLink {
//                                uriPattern = "http://test.com"
//                            }
                       ) {
                        LogIn(actionRedirect = {startActivity(navigate)},
                            backButton = {navController.navigate("home")} )
                    }
                }
            }
        }
    }
}
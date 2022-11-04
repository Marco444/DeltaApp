package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.BottomBar
import com.example.myapplication.ui.navigation.NavGraph
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { BottomBar(navController = navController)}
                ) {
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
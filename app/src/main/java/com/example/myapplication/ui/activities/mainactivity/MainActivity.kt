package com.example.myapplication.ui.activities.mainactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.activities.secondactivity.SecondActivity
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = androidx.compose.ui.graphics.Color.Black )

                val navigate = Intent(this@MainActivity, SecondActivity::class.java)

                DeltaAppInit(initialisedHandler = {finish(); startActivity(navigate)},
                            navController = navController)
            }
        }
    }
}

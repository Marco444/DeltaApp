package com.example.myapplication.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.SecondActivity
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

                //Main()

                DeltaAppInit(initialisedHandler = {startActivity(navigate)})
            }
        }
    }
}
//
//@Composable
//fun Main(
//    viewModel: MainViewModel = viewModel(factory = getViewModelFactory())
//) {
//    Text(text = "hellow")
//}
package com.example.myapplication.ui.activities.mainactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.activities.secondactivity.SecondActivity
import com.example.myapplication.ui.activities.thirdactivity.ThirdActivity
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

                val navigateRoutines = Intent(this@MainActivity, ThirdActivity::class.java)

                DeltaAppInit(initialisedHandler = {
                    if(it == -1) {
                        finish()
                        startActivity(navigateRoutines)
                    }
                    else {
                        finish()
                        val args = Bundle()
                        args.putString("routineId", it.toString())
                        navigateRoutines.putExtras(args)
                        startActivity(navigateRoutines)
                    } },
                    navController = navController)
            }
        }
    }
}

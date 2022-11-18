package com.example.myapplication.ui.activities.secondactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.example.myapplication.ui.activities.mainactivity.MainActivity
import com.example.myapplication.ui.activities.thirdactivity.ThirdActivity
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class SecondActivity : ComponentActivity() {
    val activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = androidx.compose.ui.graphics.Color.Black )

                val saved: String = intent.extras?.getString("routineId")?: "-1"

                val navigate = Intent(this@SecondActivity, ThirdActivity::class.java)
                val logoutIntent = Intent(this@SecondActivity, MainActivity::class.java)

                DeltaApp(
                    activity = activity,
                    executeRedirect = {
                        val args = Bundle()
                        args.putString("routineId", it.toString())
                        navigate.putExtras(args)
                        startActivity(navigate)
                    },
                    logoutRedirect = { finish(); startActivity(logoutIntent)},
                )
            }
        }
    }
}

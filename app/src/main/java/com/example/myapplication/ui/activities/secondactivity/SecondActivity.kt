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
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = androidx.compose.ui.graphics.Color.Black )

                val navigate = Intent(this@SecondActivity, ThirdActivity::class.java)
                val logoutIntent = Intent(this@SecondActivity, MainActivity::class.java)

                DeltaApp(
                    windowSize = calculateWindowSizeClass(this).widthSizeClass,
                    executeRedirect = {
                        val args = Bundle()
                        args.putString("routineId", it.toString())
                        navigate.putExtras(args)
                        startActivity(navigate)
                        finish()
                    },
                    logoutRedirect = { finish(); startActivity(logoutIntent)})
            }
        }
    }
}

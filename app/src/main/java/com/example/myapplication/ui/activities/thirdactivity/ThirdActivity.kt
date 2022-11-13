package com.example.myapplication.ui.activities.thirdactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.activities.secondactivity.SecondActivity
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = androidx.compose.ui.graphics.Color.Black )

                val saved: String? = intent.extras!!.getString("routineId")
                val navigate: Intent = Intent(this@ThirdActivity, SecondActivity::class.java)

                if(saved != null) {
                    Text(text = saved)
                   DeltaAppExecute(saved = saved, redirectHandler = {startActivity(navigate)}, viewModel = viewModel())
                } else {
                    Text(text = "no entre")
                }
                
            }
        }
    }
}

package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.screens.ExerciseExecScreen
import com.example.myapplication.ui.screens.RoutineDescriptionScreen
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.ExecuteRoutine
import com.example.myapplication.viewmodel.ExecuteRoutineViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                val saved: String? = intent.getStringExtra("routineId")

                if(saved != null) {
                    Text(text = saved)
                }

                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = androidx.compose.ui.graphics.Color.Black )

                val navigate = Intent(this@ThirdActivity, SecondActivity::class.java)
                if(saved != null)
                    RoutineDescriptionScreen(routineId = saved, backHandler = {startActivity(navigate)}, starRoutineHanlder = {})
            }
        }
    }
}
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.Screen
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

                val saved: String? = intent.extras!!.getString("routineId")
                val navigate: Intent = Intent(this@ThirdActivity, SecondActivity::class.java)

                if(saved != null) {
                    Text(text = saved)
                   DeltaAppExecute(saved = saved, redirectHandler = {startActivity(navigate)}, viewModel = ExecuteRoutineViewModel(saved.toInt()))
                } else {
                    Text(text = "no entre")
                }
                
            }
        }
    }
}

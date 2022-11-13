package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.viewmodel.ExecuteRoutineViewModel

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

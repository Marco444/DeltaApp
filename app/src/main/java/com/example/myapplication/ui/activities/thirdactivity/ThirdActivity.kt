package com.example.myapplication.ui.activities.thirdactivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.activities.secondactivity.SecondActivity
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.util.getExecRoutineViewModelFactory
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import okhttp3.internal.wait

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setSystemBarsColor(color = androidx.compose.ui.graphics.Color.Black )

                val saved: String? = intent.extras!!.getString("routineId")
                val navigate = Intent(this@ThirdActivity, SecondActivity::class.java)


                if(saved != null) {
                   DeltaAppExecute(
                       routineId = saved,
                       redirectHandler = {
                           finish()
                           startActivity(navigate)
                                         },
                       viewModel = viewModel(factory = getExecRoutineViewModelFactory(routineId = saved.toInt()))
                   )
                }
                
            }
        }
    }
}

package com.example.myapplication.ui.screens

import android.annotation.SuppressLint
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.Button1
import com.example.myapplication.ui.components.ExerciseExecCard
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.activities.thirdactivity.ExecuteRoutineViewModel



@Composable
fun ExerciseExecScreen(viewModel: ExecuteRoutineViewModel = viewModel(),
                       handlerBack : () ->Unit,
                       handlerFinishRoutine: ()->Unit){



    Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = handlerBack ,
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Green),
                    modifier = Modifier
                        .padding(start = 18.dp, top = 15.dp)
                        .size(55.dp)
                )
                {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "content description",
                        tint = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {


                ExerciseExecCard(actualExercise = viewModel.actualExercise ,viewModel = viewModel)
    
                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.width(300.dp)) {
                    Button1(fontSize = 13, text = "Previous", handler =
                    {
                        if(viewModel.hasPrevious())
                            viewModel.previusExercise()
                    })
                    Button1(fontSize = 13, text = "Next", handler = {
                        if(viewModel.hasNext())
                            viewModel.nextExercise()
                        else {
                            handlerFinishRoutine()
                        }
                    })

                }
//                if (popupControl) {
//                    }
                }
            }
        }
    }


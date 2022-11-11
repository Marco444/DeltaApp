package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.myapplication.ui.components.Button1
import com.example.myapplication.ui.components.ExPreviewCard
import com.example.myapplication.ui.components.ExerciseExecCard
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.theme.GreenTransparency
import com.example.myapplication.viewmodel.ExecuteRoutineViewModel
import com.example.myapplication.viewmodel.RoutinesViewModel

@Composable
fun ExerciseExecScreen(viewModel: ExecuteRoutineViewModel = ExecuteRoutineViewModel(), order:Int, routineId : String?, handlerBack : () ->Unit, handlerFinishRoutine: ()->Unit){
    var popupControl by remember { mutableStateOf(true) }
    var propierties = PopupProperties(focusable = true, dismissOnBackPress = true)
    val id = routineId?.substringAfter('}')?.toInt() ?: -1
    var oderExer = order
    Box(modifier = Modifier.background(Color(0xFF1E1E1E))) {
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
                ExerciseExecCard(nombre = viewModel.getCurrentExercise().value.name)
                Spacer(modifier = Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.width(300.dp)) {
                    Button1(fontSize = 13, text = "Previous", handler = {
                        if (oderExer != 0)
                            viewModel.nextExercise()
                            handlerFinishRoutine()

                    })
                    Button1(fontSize = 13, text = "Next", handler = {
                        if(viewModel.hasNext())
                            viewModel.nextExercise()
                        else
                            handlerFinishRoutine()
                    })

                }
                if (popupControl) {
                    Popup(
                        alignment = Alignment.BottomEnd,
                        properties = propierties

                    ) {
                        Card(backgroundColor = GreenTransparency, modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.3F)) {

                                Column(horizontalAlignment = Alignment.End) {
                                    Button(
                                        onClick = {popupControl = false },
                                        shape = CircleShape,
                                        colors = ButtonDefaults.buttonColors(backgroundColor = GreenTransparency),

                                        elevation = elevation(
                                            defaultElevation = 0.dp,
                                            pressedElevation = 0.dp
                                        )

                                    )
                                    {
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = "content description",
                                            tint = Color.Black
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(30.dp))
                                    Text(text = "To continue with the next exercise click or swipe from the right.", fontSize = 25.sp, textAlign = TextAlign.Center,modifier = Modifier.align(CenterHorizontally))

                                }
                            }


                        }
                    }
                }
            }
        }



}

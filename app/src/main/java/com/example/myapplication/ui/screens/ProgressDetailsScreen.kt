package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.RoutineProgress
import com.example.myapplication.data.Routines
import com.example.myapplication.ui.components.BackButton
import com.example.myapplication.ui.components.Button1
import com.example.myapplication.ui.theme.GrayTransparency
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.viewmodel.RoutinesViewModel


@Composable
fun SliderDelta(value: Float,
                enabled: Boolean,
                handler: (Float) -> Unit,
                color: Color = Green,
                range: ClosedFloatingPointRange<Float> = 0f..100f) {
    Slider(
        value = value,
        enabled = enabled,
        onValueChange = handler,
        valueRange = range,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color))
}

@Composable
fun ProgressDetailScreen(viewModel: RoutinesViewModel, viewRoutineHandler: () -> Unit, routineId: String?, backButtonHandler: () -> Unit) {

    val id = routineId?.substringAfter('}')?.toInt() ?: -1
    val routine: Routines = viewModel.routine(id)!!
    val routineProgress: RoutineProgress = routine.routineProgress

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.fillMaxWidth()) {
                BackButton(handler = backButtonHandler)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(top = 20.dp),
            ) {

                Text(text = routine.title, style = MaterialTheme.typography.h1)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 50.dp) //este padding sirve como margin
                        .clip(RoundedCornerShape(30.dp))
                        .fillMaxWidth(0.7f)
                        .background(GrayTransparency)
                        .padding(20.dp) //este como padding per se, ya con el background
                ) {


                    Text(
                        text = routineProgress.progressTile(),
                        style = MaterialTheme.typography.h3,
                        color = routineProgress.color()
                    )

                    SliderDelta(
                        routineProgress.agreggatePerformance,
                        false,
                        {},
                        routineProgress.color()
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = routineProgress.progressDescription(),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }

            }

            Spacer(modifier = Modifier.height(20.dp))

//        Row(verticalAlignment = Alignment.CenterVertically){
//            Button1(fontSize = 20, text = stringResource(R.string.see_routine_details), handler = viewRoutineHandler)
//        }
        }
    }
}

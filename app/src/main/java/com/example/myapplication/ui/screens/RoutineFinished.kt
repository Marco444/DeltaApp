package com.example.myapplication.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.components.Button1
import com.example.myapplication.ui.theme.*
import com.example.myapplication.ui.activities.thirdactivity.ExecuteRoutineViewModel
import com.example.myapplication.ui.components.Stars
import okhttp3.internal.wait


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RoutineFinished(
    viewModel: ExecuteRoutineViewModel,
    viewRoutineHandler: () -> Unit,
    routineId: String,
    backButtonHandler: () -> Unit,
    nextHandler : () -> Unit,
    errorRedirect: () -> Unit
) {
    val routine by viewModel.executeRoutine.value.currentRoutine.collectAsState()

    val fetchingState by viewModel.fetchingState.collectAsState()
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background),
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxHeight(0.1F),
            verticalAlignment = CenterVertically
        ) {
            Text(text = routine.title, fontFamily = H1Font, fontSize = 50.sp, color = Green)
        }
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
                text = "Well done",
                fontFamily = H1Font,
                fontSize = 60.sp,
                color = Color.White
            )
            var delta = 0f
            var average = 0.0
            if(routine.delta?.isNotEmpty()?:false)
                average = routine.delta?.average()?:0.0
            println(average)
            if(average != 0.0)
                 delta = viewModel.getCurrentDelta().div(average?:1.0).toFloat()
            else
                delta = viewModel.getCurrentDelta()
            val color : Color = if(delta < 0.3) {
                Red
            }else if(delta < 0.7) {
                Yellow
            }
            else {
                Green
            }

            LinearProgressIndicator(
                progress = delta,
                color = color,
                modifier = Modifier
                    .height(15.dp)
                    .clip(RoundedCornerShape(30.dp))
            )
            Text(
                text = "You have surpassed the expected progress for today's workout, by 20%",
                fontFamily = NormalFont,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Stars(routine = routine, clickable = true)

            Spacer(modifier = Modifier.height(20.dp))
            Button1(
                fontSize = 13,
                text = "Finish",
                handler = {
                        viewModel.finishRoutine()
                        if(!fetchingState.error)
                            nextHandler()
                        else{
                            setSnackBarState(true)
                        }



                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
    // The Snackbar
    if (snackbarVisibleState) {
        Snackbar(
            action = {
                Button(
                    onClick = {setSnackBarState(!snackbarVisibleState)},
                ) {
                    Text(text = stringResource(id = R.string.try_again))
                }
            },
            modifier = Modifier.padding(8.dp)

        ) { Text(text =  fetchingState.message?: "") }
    }
}

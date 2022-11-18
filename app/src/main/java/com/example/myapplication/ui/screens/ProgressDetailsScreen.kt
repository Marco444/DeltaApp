package com.example.myapplication.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.classes.RoutineProgress
import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.ui.components.BackButton
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel
import com.example.myapplication.ui.components.BackgroundRoutineImage
import com.example.myapplication.ui.components.Chart



@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProgressDetailScreen(viewModel: RoutinesViewModel, viewRoutineHandler: () -> Unit, routineId: String?, backButtonHandler: () -> Unit, errorRedirect: () -> Unit) {

    val id = routineId?.substringAfter('}')?.toInt() ?: -1
    val routine by viewModel.selectRoutine.collectAsState()
    val fetchingState by viewModel.fetchingState.collectAsState()

    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }

    val routineProgress: RoutineProgress = routine.routineProgress
    var showChart by remember {
        mutableStateOf(true)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background),
    ) {

        Box() {
           BackgroundRoutineImage(routine = routine)
            Column(verticalArrangement = Arrangement.SpaceEvenly) {

                Column(modifier = Modifier.fillMaxWidth()) {

                    BackButton(handler = backButtonHandler)


                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 10.dp),
                    ) {

                        Text(text = routine.title, style = MaterialTheme.typography.h1, fontSize = 50.sp)

                        Text(
                            text = stringResource(id = R.string.explanationProgress),
                            style = MaterialTheme.typography.h3,
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(top = 50.dp) //este padding sirve como margin
                                .clip(RoundedCornerShape(30.dp))
                                .fillMaxWidth(0.8f)
                                .background(MaterialTheme.colors.background)
                                .padding(20.dp) //este como padding per se, ya con el background
                        ) {

                            if(routine.delta?.isNotEmpty() == true) {
                                var i = 0
                                lateinit var map : Map<Int,Float>
                                if((routine.delta?.size?.compareTo(10) ?: 0) < 0)
                                     map = routine.delta?.associate { (i++) to it }?: emptyMap()
                                else{

                                    map = routine.delta?.slice(
                                        (routine.delta?.size?.minus(
                                            10
                                        ))?.rangeTo((routine.delta?.size!! - 1)) ?: 0..10
                                    )?.associate { (i++) to it }?: emptyMap()
                                }
                                Box() {

                                    Chart(
                                        data = map ?: emptyMap(), height = 250.dp,
                                        isExpanded = showChart,
                                        bottomEndRadius = 20.dp,
                                        bottomStartRadius = 20.dp
                                    )
                                }
                            } else {
                                Text(
                                    text = "You have not yet executed this routine!",
                                    style = MaterialTheme.typography.h4,
                                    fontSize = 20.sp,
                                    color = Color.White,
                                    modifier = Modifier.fillMaxWidth(0.8f)
                                )
                            }

                        }
                    }

                }
            }
        }
    }
    LaunchedEffect(key1 = fetchingState.error){
        if(fetchingState.error)
            setSnackBarState(true)
    }


    // The Snackbar
    if (snackbarVisibleState) {
        Snackbar(
            action = {
                Button(
                    onClick = {setSnackBarState(!snackbarVisibleState);backButtonHandler()},
                ) {
                    Text(text = stringResource(id = R.string.go_back))
                }
            },
            modifier = Modifier.padding(8.dp)

        ) { Text(text =  fetchingState.message) }
    }

}


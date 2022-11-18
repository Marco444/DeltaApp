package com.example.myapplication.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.ui.components.Button1
import com.example.myapplication.ui.components.ExPreviewCard
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.activities.thirdactivity.ExecuteRoutineViewModel
import com.example.myapplication.ui.components.SimpleCircularProgressComponent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RoutineDescriptionScreen(viewModel: ExecuteRoutineViewModel,
                             routineId: String,
                             backHandler : () -> Unit,
                             starRoutineHanlder : () -> Unit,
                             starRoutineLiteHandler: () -> Unit,
                             errorRedirect: () -> Unit,
                             liteMode: Boolean
                             ){


    val error by viewModel.error.collectAsState()
    val fetchState by viewModel.fetchingState.collectAsState()
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }



    val routine by viewModel.executeRoutine.value.currentRoutine.collectAsState()

    Box(modifier = Modifier.background(Color(0xFF1E1E1E))) {
     Column(verticalArrangement = Arrangement.SpaceEvenly) {
         Column(modifier = Modifier.fillMaxWidth()) {
             Button(
                 onClick = backHandler ,
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
             ExPreviewCard(routine.title ?: "",30,routine.description ?: "")
             Spacer(modifier = Modifier.height(40.dp))
             ListOfExercises(viewModel)
             Spacer(modifier = Modifier.height(10.dp))
             Row(
                 verticalAlignment = Alignment.CenterVertically,
                 modifier = Modifier.align(Alignment.CenterHorizontally)
             ) {
                 if(!fetchState.isFetching) {
                     Button1(
                         fontSize = 17,
                         text = "Start Routine",
                         handler = {
                             if (viewModel.hasNext()) {
                                 viewModel.nextExercise()
                                 if (viewModel.getExecuteRoutineLiteMode())
                                     starRoutineLiteHandler()
                                 else
                                     starRoutineHanlder()
                             } else {
                                 backHandler()
                             }
                         }
                     )
                 }
             }
         }
     }

    }
    if(fetchState.isFetching){
        SimpleCircularProgressComponent()
    }
    LaunchedEffect(key1 = fetchState.error, block = {
        if(fetchState.error)
            setSnackBarState(true)
    })
    // The Snackbar
    if (snackbarVisibleState) {
        Snackbar(
            action = {
                Button(
                    onClick = { setSnackBarState(!snackbarVisibleState);backHandler() },
                ) {
                    Text(text = "Go back")
                }
            },
            modifier = Modifier.padding(8.dp)

        ) { Text(text = fetchState.message) }
    }

}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ListOfExercises(viewModel: ExecuteRoutineViewModel){
    val warmUp by viewModel.executeRoutine.value.exercises[0].collectAsState()
    val mainSet by viewModel.executeRoutine.value.exercises[1].collectAsState()
    val coolDown by viewModel.executeRoutine.value.exercises[2].collectAsState()
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight(0.8F)
    ){
        item {
            Text(text = "WarmUp", fontSize = 30.sp, color = Green)

        }
        items(warmUp) { item ->
                   Text(text = item.name, fontSize = 30.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(1.dp))
            }
        item {
            Text(text = "mainSet", fontSize = 30.sp, color = Green)

        }
        items(mainSet){ item ->
                Text(text = item.name, fontSize = 30.sp, color = Color.White)
                Spacer(modifier = Modifier.height(1.dp))
            }
        item {
            Text(text = "CoolDown", fontSize = 30.sp, color = Green)

        }
        items(coolDown){ item ->
            Text(text = item.name, fontSize = 30.sp, color = Color.White)
            Spacer(modifier = Modifier.height(1.dp))
        }
        }
}


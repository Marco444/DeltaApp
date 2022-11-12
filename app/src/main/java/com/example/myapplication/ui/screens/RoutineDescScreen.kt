package com.example.myapplication.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.RoutineProgress
import com.example.myapplication.data.Routines
import com.example.myapplication.ui.components.Button1
import com.example.myapplication.ui.components.ExPreviewCard
import com.example.myapplication.ui.components.ROUTINE_CARD_WIDTH
import com.example.myapplication.ui.components.RoutineCard
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.ExecuteRoutine
import com.example.myapplication.viewmodel.ExecuteRoutineViewModel
import com.example.myapplication.viewmodel.RoutinesViewModel

@Composable
fun RoutineDescriptionScreen(viewModel: ExecuteRoutineViewModel = ExecuteRoutineViewModel(), routineId: String,backHandler : () -> Unit,starRoutineHanlder : ()->Unit){


    val routine: Routines = viewModel.routine(routineId.toInt())

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
             Button1(fontSize = 20, text = "Start Routine", handler = starRoutineHanlder)
         }
     }

    }
}

@Composable
fun ListOfExercises(viewModel: ExecuteRoutineViewModel){
    LazyColumn( horizontalAlignment = Alignment.CenterHorizontally){
        item {
            Text(text = "WarmUp", fontSize = 30.sp, color = Green)

        }
        items(viewModel.getRoutineWarmUpExercises()) { item ->
                   Text(text = item.name, fontSize = 30.sp, color = Color.White)
                    Spacer(modifier = Modifier.height(1.dp))
            }
        item {
            Text(text = "mainSet", fontSize = 30.sp, color = Green)

        }
        items(viewModel.getRoutineMainSetExercises()){ item ->
                Text(text = item.name, fontSize = 30.sp, color = Color.White)
                Spacer(modifier = Modifier.height(1.dp))
            }
        item {
            Text(text = "CoolDown", fontSize = 30.sp, color = Green)

        }
        items(viewModel.getRoutineCoolDownExercises()){ item ->
            Text(text = item.name, fontSize = 30.sp, color = Color.White)
            Spacer(modifier = Modifier.height(1.dp))
        }
        }
}


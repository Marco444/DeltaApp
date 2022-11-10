package com.example.myapplication.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
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
import com.example.myapplication.viewmodel.RoutinesViewModel

@Composable
fun RoutineDescriptionScreen(viewModel: RoutinesViewModel, routineId: String?){

    val id = routineId?.substringAfter('}')?.toInt() ?: -1

    val routine: Routines? = viewModel.routine(id)

    Box(modifier = Modifier.background(Color(0xFF1E1E1E))) {
     Column(verticalArrangement = Arrangement.SpaceEvenly) {
         Column(modifier = Modifier.fillMaxWidth()) {

             Button(
                 onClick = { } ,
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
             ExPreviewCard("Ejercicio $id",30,"Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsumLorem ipsumLorem ipsum")
             ListOfExercises()
             Spacer(modifier = Modifier.height(20.dp))
             Button1(fontSize = 20, text = "Start Routine")
         }
     }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListOfExercises(){
    val otherStrings = arrayOf("a", "b", "c","c","c","c","c","c","c","c","c","c","c","c","c","c","c")
    LazyColumn(modifier = Modifier.height(450.dp), horizontalAlignment = Alignment.CenterHorizontally){
            items(otherStrings) { routine ->
               if(! routine.equals( "Cooldown") ||! routine.equals("mainSet") || !routine.equals( "warmUp" )) {
                   Text(text = routine, fontSize = 30.sp, color = Color.White)
               }else{
                   Text(text = routine, fontSize = 30.sp, color = Green)
               }
                    Spacer(modifier = Modifier.height(1.dp))
            }
        }
}

//
//@Preview
//@Composable
//fun preview() {
//    routineDesc()
//}
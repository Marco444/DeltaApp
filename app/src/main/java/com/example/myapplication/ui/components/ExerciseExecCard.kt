package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExerciseExecCard(nombre : String){
    Card(backgroundColor = Color.DarkGray) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize(1F)) {
            Text(text = nombre, fontSize = 15.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                VariantCards(name = "Reps", cant = 2)
                Spacer(modifier = Modifier.width(30.dp))
                VariantCards(name = "Weight", cant = 2)
            }

        }

    }
}
@Composable
fun VariantCards(name : String,cant : Int){
    val range = 25f..50f
    var selection by remember { mutableStateOf(cant.toFloat()) }

    Card(backgroundColor = Color.LightGray, elevation = 3.dp, modifier = Modifier.width(130.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = name, fontSize = 15.sp)
            Text(text = selection.toInt().toString(), fontSize = 15.sp)
            Slider(
                value = selection,
                valueRange = range,
                onValueChange = { selection = it }
            )
        }


    }
}
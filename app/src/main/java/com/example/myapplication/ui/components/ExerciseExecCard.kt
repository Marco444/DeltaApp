package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Green

@Composable
fun ExerciseExecCard(nombre : String){
    Card(backgroundColor = Color.LightGray,shape = RoundedCornerShape(30.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth(0.8F).fillMaxHeight(0.8F), verticalArrangement = Arrangement.SpaceEvenly){
            Text(text = nombre, fontSize = 40.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                VariantCards(name = "Reps", cant = 2)
                Spacer(modifier = Modifier.width(30.dp))
                VariantCards(name = "Weight", cant = 2)
            }

        }

    }
}
// TODO NO SE PORQUE NO FUNCIONA LO DEL NUMERO, PREGUNTAR HOY
@Composable
fun VariantCards(name : String,cant : Int){
    var value by remember { mutableStateOf(30f) }

    Card(backgroundColor = Color.LightGray, elevation = 6.dp, modifier = Modifier.width(120.dp).fillMaxHeight(0.4F),shape = RoundedCornerShape(20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = name, fontSize = 25.sp)
            Text(text = value.toInt().toString(), fontSize = 20.sp)
            Slider(
                value = value,
                steps = 0,
                valueRange = 25f..50f,
                colors = SliderDefaults.colors(
                    activeTickColor = Green,
                    inactiveTickColor = Green,
                    inactiveTrackColor = Color.DarkGray,
                    activeTrackColor = Green,
                    thumbColor = Green
                ),
                onValueChange = { newValue ->
                    value = newValue
                }
            )

        }


    }
}
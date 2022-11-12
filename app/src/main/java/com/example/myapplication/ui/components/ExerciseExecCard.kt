package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.Exercise
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.viewmodel.ExecuteRoutineViewModel
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun RecomposingTitle(exercise: MutableStateFlow<Exercise>) {
    val text by exercise.collectAsState()
    Text(text = text.name, modifier = Modifier.padding((text.order % 2).dp))
}

@Composable
fun ExerciseExecCard(viewModel: ExecuteRoutineViewModel, actualExercise: MutableStateFlow<Exercise>){

    val exercise by actualExercise.collectAsState()

    Card(backgroundColor = if(exercise.order % 2 == 0) Green else Gray,shape = RoundedCornerShape(30.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth(0.8F)
            .fillMaxHeight(0.8F), verticalArrangement = Arrangement.SpaceEvenly){
            RecomposingTitle(exercise = actualExercise)
            Row(verticalAlignment = Alignment.CenterVertically) {
                VariantCards(name = "Reps",
                    { exercise.weight }, handler = {viewModel.setWeight(it)})
                Spacer(modifier = Modifier.width(30.dp))
                VariantCards(name = "Weight",
                    { exercise.repetitions },handler = {viewModel.setReps(it)})
            }

        }

    }
}
@Composable
fun VariantCards(name : String,handlerGetValue : ()->Int,handler : (Float) -> Unit){
    var value by remember { mutableStateOf(30f) }
    println(value)
    Card(backgroundColor = Color.LightGray, elevation = 6.dp, modifier = Modifier
        .width(120.dp)
        .fillMaxHeight(0.4F),shape = RoundedCornerShape(20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = name, fontSize = 25.sp)
            Text(text = handlerGetValue().toString(), fontSize = 20.sp)
            Slider(
                value =handlerGetValue().toFloat(),
                steps = 0,
                valueRange = 0f..10000f,
                colors = SliderDefaults.colors(
                    activeTickColor = Green,
                    inactiveTickColor = Green,
                    inactiveTrackColor = Color.DarkGray,
                    activeTrackColor = Green,
                    thumbColor = Green
                ),
                onValueChange = handler
            )

        }


    }
}
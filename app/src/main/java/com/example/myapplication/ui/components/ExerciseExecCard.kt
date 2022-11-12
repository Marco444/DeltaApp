package com.example.myapplication.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
    Text(text = text.name)
}

@Composable
fun ExerciseExecCard(viewModel: ExecuteRoutineViewModel, actualExercise: MutableStateFlow<Exercise>){

    val exercise by actualExercise.collectAsState()

    Card(backgroundColor = Gray,shape = RoundedCornerShape(30.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth(0.8F)
            .fillMaxHeight(0.8F), verticalArrangement = Arrangement.SpaceEvenly){
            RecomposingTitle(exercise = actualExercise)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RepsCards(name = "Reps",
                    actualExercise = actualExercise , viewModel = viewModel)
                Spacer(modifier = Modifier.width(30.dp))
                WeightCards(name = "Weight",
                    actualExercise = actualExercise , viewModel = viewModel)
            }

        }

    }
}
@SuppressLint("UnrememberedMutableState")
@Composable
fun WeightCards(name : String,  actualExercise: MutableStateFlow<Exercise>,viewModel: ExecuteRoutineViewModel){
    val exercise by actualExercise.collectAsState()
    var sliderValueRaw by remember { mutableStateOf(exercise.weight) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDragged by interactionSource.collectIsDraggedAsState()
    val isInteracting = isPressed || isDragged
    val sliderValue by derivedStateOf {
        if (isInteracting) {
            sliderValueRaw
        } else {
            exercise.weight
        }
    }

    Card(backgroundColor = Color.LightGray, elevation = 6.dp, modifier = Modifier
        .width(120.dp)
        .fillMaxHeight(0.4F),shape = RoundedCornerShape(20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = name, fontSize = 25.sp)
            Text(text = sliderValue.toInt().toString(), fontSize = 20.sp)
            Slider(
                value = sliderValue, // using calculated sliderValue here from above
                onValueChange = {
                    sliderValueRaw = it
                },
                onValueChangeFinished = {
                    viewModel.setWeight(sliderValue)
                },
                interactionSource = interactionSource,
                valueRange = 0f..300f,
                colors = SliderDefaults.colors(
                    activeTickColor = Green,
                    inactiveTickColor = Green,
                    inactiveTrackColor = Color.DarkGray,
                    activeTrackColor = Green,
                    thumbColor = Green
                ),
            )


        }


    }
}
@SuppressLint("UnrememberedMutableState")
@Composable
fun RepsCards(name : String,  actualExercise: MutableStateFlow<Exercise>,viewModel: ExecuteRoutineViewModel){
    val exercise by actualExercise.collectAsState()
    var sliderValueRaw by remember { mutableStateOf(exercise.repetitions) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDragged by interactionSource.collectIsDraggedAsState()
    val isInteracting = isPressed || isDragged
    val sliderValue by derivedStateOf {
        if (isInteracting) {
            sliderValueRaw
        } else {
            exercise.repetitions
        }
    }

    Card(backgroundColor = Color.LightGray, elevation = 6.dp, modifier = Modifier
        .width(120.dp)
        .fillMaxHeight(0.4F),shape = RoundedCornerShape(20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = name, fontSize = 25.sp)
            Text(text = sliderValue.toInt().toString(), fontSize = 20.sp)
            Slider(
                value = sliderValue, // using calculated sliderValue here from above
                onValueChange = {
                    sliderValueRaw = it
                },
                onValueChangeFinished = {
                    viewModel.setReps(sliderValue)
                },
                interactionSource = interactionSource,
                valueRange = 0f..300f,
                colors = SliderDefaults.colors(
                    activeTickColor = Green,
                    inactiveTickColor = Green,
                    inactiveTrackColor = Color.DarkGray,
                    activeTrackColor = Green,
                    thumbColor = Green
                ),
            )


        }


    }
}
package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.classes.Exercise
import com.example.myapplication.ui.activities.thirdactivity.ExecuteRoutineViewModel
import com.example.myapplication.ui.components.*
import com.example.myapplication.ui.theme.*

@Composable
fun ExerciseExecuteScreenAlternative(
    viewModel: ExecuteRoutineViewModel = viewModel(),
    handlerBack : () ->Unit,
    handlerFinishRoutine: ()->Unit
){
    Box(modifier = Modifier.background(backGround)) {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = handlerBack,
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

                Text(
                    text = viewModel.routine(0).title,
                    style = MaterialTheme.typography.h1,
                    fontFamily = H1Font,
                    color = Green,
                    modifier = Modifier.align(CenterHorizontally)
                )


                Spacer(modifier = Modifier.height(10.dp))


                val exer: List<Exercise> = viewModel.getExercises()

                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(0.85F)
                        .align(CenterHorizontally)
                        .padding(top = 0.dp),
                    //horizontalAlignment = CenterHorizontally
                ){
                    items(exer) {exer ->
                        ExerciseCard(
                            exercise = exer,
                            viewModel = viewModel
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                ) {

                    Row(
                        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button1(
                            fontSize = 15,
                            text = "Previous",
                            modifier = Modifier.align(CenterVertically)
                                .padding(10.dp)
                        )
                        Button1(
                            fontSize = 15,
                            text = "Next",
                            modifier = Modifier.align(CenterVertically)
                                .padding(10.dp)
                        )
                    }

                }

            }
        }

    }
}

@Preview
@Composable
fun TryALternative(){
    ExerciseExecuteScreenAlternative(handlerBack = {}) {

    }
}

@Composable
fun RoutineInfo(title : String, time: Int, description: String){
    Card(modifier = Modifier.width(300.dp),shape = RoundedCornerShape(20.dp), backgroundColor = Color.DarkGray){
        Column(horizontalAlignment = CenterHorizontally) {
            Text(text = title, color = Green, fontSize = 25.sp)
            TimeComp(time)
            Text(text = description, color = Color.White, fontSize = 13.sp,textAlign = TextAlign.Center,
            )
        }
    }
}


@Composable
fun ExerciseCard(
    viewModel: ExecuteRoutineViewModel = viewModel(),
    exercise: Exercise
){
    var expanded by remember { mutableStateOf(!viewModel.cardsExpandable()) }
    //val exercise by actualExercise.collectAsState()

    Box (
        Modifier
            //.width(ROUTINE_CARD_WIDTH.dp)
            .fillMaxWidth(0.9F)
            .background(Gray)
            .border(4.dp, if (expanded) Green else Gray, shape = RoundedCornerShape(10.dp))
            .clickable {
                expanded = if (viewModel.cardsExpandable()) !expanded else true
            }
            .clip(RoundedCornerShape(10.dp)),
    ){

        Column () {
            Text(
                text = exercise.name,
                fontFamily = NormalFont,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
            )
            if (expanded) {
                Text(
                    text = exercise.detail,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
                )
            }
        }

    }
    Spacer(modifier = Modifier.height(30.dp))
}
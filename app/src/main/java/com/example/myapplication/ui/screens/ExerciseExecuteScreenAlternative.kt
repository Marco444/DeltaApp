package com.example.myapplication.ui.screens

import android.view.RoundedCorner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.data.Exercise
import com.example.myapplication.ui.activities.thirdactivity.ExecuteRoutineViewModel
import com.example.myapplication.ui.components.*
import com.example.myapplication.ui.navigation.NavBarScreen
import com.example.myapplication.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow

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
                    text = "Hola",
                    style = MaterialTheme.typography.h1,
                    fontFamily = H1Font,
                    color = Green,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )


                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    ExerciseCard(
                        actualExercise = viewModel.actualExercise,
                        viewModel = viewModel
                    )
                    //Spacer(modifier = Modifier.height(5.dp))
                    ExerciseCard(
                        actualExercise = viewModel.actualExercise,
                        viewModel = viewModel
                    )

                }


                LazyColumn(modifier = Modifier.fillMaxHeight()){

                }


//                        RoutinesGrid(
//                            viewModel = viewModel,
//                            actionRedirect = actionRedirect,
//                            routineCard = RoutineCard.MyRoutine
//                        )



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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
    actualExercise: MutableStateFlow<Exercise>
){
    var expanded by remember { mutableStateOf(!viewModel.cardsExpandable()) }
    val exercise by actualExercise.collectAsState()

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
package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.BackButton
import com.example.myapplication.ui.components.Button1
import com.example.myapplication.ui.theme.*
import com.example.myapplication.viewmodel.RoutinesViewModel

@Composable
fun RoutineFinished(
    viewModel: RoutinesViewModel,
    viewRoutineHandler: () -> Unit,
    routineId: String?,
    backButtonHandler: () -> Unit
){
    var actual: Int = 2
    val opinions: List<String> = listOf("Aweful", "Bad", "Regular", "Good", "Excelent")
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp),
            ) {

                Text(text = "asff", fontFamily = H1Font, fontSize = 60.sp, color = Green)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 50.dp) //este padding sirve como margin
                        .clip(RoundedCornerShape(30.dp))
                        .fillMaxWidth(0.7f)
                        .background(GrayTransparency)
                        .padding(20.dp) //este como padding per se, ya con el background
                ) {
                    Text(text = "Well done", fontFamily = H1Font, fontSize = 60.sp, color = Color.White)
                    LinearProgressIndicator(
                        progress = 0.7f,
                        color = Green,
                        modifier = Modifier
                            .height(15.dp)
                            .clip(RoundedCornerShape(30.dp))
                    )
                    Text(
                        text = "You have surpassed the expected progress for todays workout, by 20%",
                        fontFamily = NormalFont,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Review this routine:",
                        fontFamily = NormalFont,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        modifier = Modifier.align(CenterHorizontally),
                        verticalAlignment = CenterVertically

                    ){
                        Button(
                            onClick = { viewModel.downOpinion() },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                            elevation = ButtonDefaults.elevation(0.dp),
                        ) {
                            Icon(
                                Icons.Default.Remove,
                                contentDescription = "-",
                                tint = Color.Red,
                            )
                        }
                        Text(
                            text = opinions[viewModel.getActualOpinion()],
                            fontFamily = NormalFont,
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        Button(
                            onClick = { viewModel.upOpinion() },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                            elevation = ButtonDefaults.elevation(0.dp),
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = "+",
                                tint = Color.Green
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Button1(fontSize = 13, text = "Next", handler = {})
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

}

@Preview
@Composable
fun tryFinished(){
    RoutineFinished(viewModel = RoutinesViewModel(), viewRoutineHandler = { /*TODO*/ }, backButtonHandler = {}, routineId = "as")
}
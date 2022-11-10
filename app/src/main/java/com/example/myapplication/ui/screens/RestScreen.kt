package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.Button1
import com.example.myapplication.ui.components.ExerciseExecCard
import com.example.myapplication.ui.theme.Green

@Composable
fun RestScreen(){

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
                Spacer(modifier = Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.width(300.dp)) {
                    Button1(fontSize = 13, text = "Previous")
                    Button1(fontSize = 13, text = "Next")

                }
            }
        }

    }
}

@Preview
@Composable
fun RestScreenPrev() {
    RestScreen()
}
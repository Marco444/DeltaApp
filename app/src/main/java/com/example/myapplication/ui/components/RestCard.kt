package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RestCard(time : Int){
    Card(backgroundColor = Color.LightGray,shape = RoundedCornerShape(30.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth(0.8F).fillMaxHeight(0.8F), verticalArrangement = Arrangement.SpaceEvenly){
            Text(text = "Rest", fontSize = 40.sp)


        }

    }
}
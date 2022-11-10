package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Green

@Composable
fun ExPreviewCard(title : String, time:Int,description :String){
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
fun TimeComp(time : Int){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            Icons.Default.Timer,
            contentDescription = "Timer",
            tint = Color.Black
        ) 
        Text(text = "$time mn", color = Color.White, fontSize = 10.sp)
    }
}
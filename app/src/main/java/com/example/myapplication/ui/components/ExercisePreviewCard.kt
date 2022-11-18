package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Green

@Composable
fun ExPreviewCard(title : String){
    Card(modifier = Modifier.width(300.dp),shape = RoundedCornerShape(20.dp), backgroundColor = Color.DarkGray){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, color = Green, fontSize = 25.sp)
        }
    }
}
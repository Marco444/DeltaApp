package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RoutineCardSortButton() {
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Gray)
            .padding(5.dp),

        ){
        SortButton(onClick ={}, txt = "Favourite")
        SortButton(onClick ={}, txt = "Recent")
        SortButton(onClick ={}, txt = "Costum")
    }
}
@Composable
fun SortButton(onClick: () -> Unit, txt: String) {
    Box(Modifier.clickable(onClick = onClick)) {
        Text(
            text = txt,
            fontSize = 25.sp,
            color = Color.White,
            modifier = Modifier.padding(6.dp),
        )
    }
}

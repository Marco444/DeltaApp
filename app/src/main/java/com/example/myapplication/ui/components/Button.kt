package com.example.myapplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Green

@Composable
fun Button1(fontSize: Int, text: String, handler: () -> Unit = {}) {
    Button(
        onClick = handler,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        border = BorderStroke(3.dp, Green),
        shape = RoundedCornerShape(20)
    ) {
        Text(text = text,
            fontSize = fontSize.sp,
            color = Green,
            modifier = Modifier.padding(7.dp)
        )
    }
}

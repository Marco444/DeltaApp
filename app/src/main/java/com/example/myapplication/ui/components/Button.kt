package com.example.myapplication.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.BackgroundButton
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.theme.H1Font


@Composable
fun Button1(fontSize: Int, text: String, handler: () -> Unit = {}, modifier: Modifier = Modifier) {
    Button(
        onClick = handler,
        colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundButton),
        border = BorderStroke(3.dp, Green),
        shape = RoundedCornerShape(20),
        modifier = modifier
    ) {
        Text(text = text,
            fontSize = fontSize.sp,
            fontFamily = H1Font,
            color = Green,

            modifier = modifier.padding(7.dp)
        )
    }
}


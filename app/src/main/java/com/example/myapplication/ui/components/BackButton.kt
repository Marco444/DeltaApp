package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Green



@Composable
fun BackButton(handler: () -> Unit) {

    Button(onClick = handler,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Green),
        modifier = Modifier.padding(start = 18.dp, top = 15.dp).size(55.dp))
    {
        Icon(Icons.Default.ArrowBack ,contentDescription = "content description", tint= Color.Black)
    }
}
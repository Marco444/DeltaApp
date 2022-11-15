package com.example.myapplication.ui.components

import Base64BitMap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.example.myapplication.ui.classes.Routines

@Composable
fun BackgroundRoutineImage(routine: Routines) {
    val bitmap = Base64BitMap(routine.img)?.asImageBitmap()
    if (bitmap != null) {
        Image(
            bitmap = bitmap,
            contentDescription = "Routine Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alpha = 0.5f
        )
    }
}
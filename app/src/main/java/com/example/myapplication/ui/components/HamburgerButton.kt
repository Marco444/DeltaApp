package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Green


@Composable
fun HamburgerButton(modifier: Modifier){

    Button(onClick = { } ,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        modifier = modifier,
        elevation = ButtonDefaults.elevation(0.dp)

    )
    {
        Icon(
            Icons.Default.Menu,
            contentDescription = "content description",
            tint= Green,
            modifier = Modifier.scale(2F)
        )
    }
}

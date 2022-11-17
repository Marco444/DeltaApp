package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Green

@Composable
fun SettingsButton(
    modifier: Modifier,
    onClick : () -> Unit
){
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        modifier = modifier.padding(0.dp),
    )
    {
        Icon(
            Icons.Outlined.Settings,
            contentDescription = "hamburger icon",
            tint = Green,
            modifier = Modifier.scale(1.4F)
        )
    }
}
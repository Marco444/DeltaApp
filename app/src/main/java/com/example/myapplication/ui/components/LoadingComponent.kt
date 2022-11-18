package com.example.myapplication.ui.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Green

@Composable
fun SimpleCircularProgressComponent() {

    Column(

        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(16.dp),
            color = Green,
            strokeWidth = Dp(value = 4F)
        )
    }
}
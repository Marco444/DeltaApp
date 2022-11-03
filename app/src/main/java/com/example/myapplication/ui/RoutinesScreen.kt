package com.example.myapplication.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.R
import com.example.myapplication.ui.components.RoutineCard
import com.example.myapplication.ui.components.RoutineCardSortButton

@Composable
fun RoutinesScreen(){
    Column (
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Progress",
            fontSize = 70.sp,
            color = Green,
        )

        Spacer(modifier = Modifier.height(10.dp))

        RoutineCardSortButton()

        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(20) {
                RoutineCard(
                    backgroundImageId = R.drawable.registration_background,
                    iconIdUnclicked = R.drawable.star_border_white_24dp,
                    iconIdClicked = R.drawable.star_rate_white_24dp,
                    title = "Routine #$it"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoutinesPreview() {
    RoutinesScreen();
}

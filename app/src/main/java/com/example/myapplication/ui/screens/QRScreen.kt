package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.theme.H1Font
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel

@Composable
fun QRScreen(viewModel: RoutinesViewModel) {
    Column(
        modifier = Modifier
            .background(Color(0xFF1E1E1E))
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.1F))
        Text(
            text = "Scan a Routine",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxHeight(0.2F)
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.1F))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xAFCFFFB3))
                .width(300.dp)
                .height(300.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxHeight(0.6F)
        ){

        }
        Spacer(modifier = Modifier.fillMaxHeight(0.1F))
    }
}


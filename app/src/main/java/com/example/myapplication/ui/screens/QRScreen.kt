package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.viewmodel.RoutinesViewModel

@Composable
fun QRScreen(viewModel: RoutinesViewModel) {
    Box(modifier = Modifier.background(Color(0xFF1E1E1E)).fillMaxWidth().fillMaxHeight()) {
        Text(text = "QR")
    }
}

@Preview
@Composable
fun runQR(){
    QRScreen(viewModel = RoutinesViewModel())
}
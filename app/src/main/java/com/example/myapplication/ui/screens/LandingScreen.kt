package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.myapplication.R
import com.example.myapplication.ui.components.Button1


@Composable
fun BackgroundImage(painter: Painter) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}


@Composable
fun LandingScreen(loginHandler: () -> Unit, tryOutHandler: () -> Unit) {
    Box {
        BackgroundImage(painter = painterResource(id = R.drawable.registration_background))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Image(
                painter = painterResource(id = R.drawable.delta_logo),
                contentDescription = "delta symbol of greek alphabet"
            )
            Button1(fontSize = 42, text = stringResource(R.string.try_out), handler = tryOutHandler)

            Row {
                Button1(fontSize = 24, text = stringResource(R.string.login), handler = loginHandler)
            }
        }
    }
}




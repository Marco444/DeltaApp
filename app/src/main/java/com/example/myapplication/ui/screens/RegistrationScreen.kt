package com.example.myapplication.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color.Companion.Green as Green1
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
fun RegistrationScreen(actionRedirect: () -> Unit) {
    Box {
        BackgroundImage(painter = painterResource(id = R.drawable.registration_background))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.delta_logo),
                contentDescription = "delta symbol of greek alphabet"
            )
            Spacer(modifier = Modifier.height(200.dp))
            Button1(fontSize = 42, text = stringResource(R.string.try_out))
            Spacer(modifier = Modifier.height(100.dp))

            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Button1(fontSize = 24, text = stringResource(R.string.login), handler = actionRedirect)
                Spacer(modifier = Modifier.width(20.dp))
                Button1(fontSize = 24, text = stringResource(R.string.sing_up)) {}
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

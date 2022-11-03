package com.example.myapplication.ui

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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color.Companion.Green as Green1
import com.example.myapplication.R

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
fun ButtonRegistration(fontSize: Int, text: String, handler: () -> Unit = {}) {
    Button(
        onClick = handler,
        colors = ButtonDefaults.buttonColors(backgroundColor = Black),
        border = BorderStroke(3.dp, Green1),
        shape = RoundedCornerShape(20)
    ) {
        Text(text = text,
            fontSize = fontSize.sp,
            color = Green1,
            modifier = Modifier.padding(7.dp)
        )
    }
}

@Composable
fun RegistrationScreen() {
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
            ButtonRegistration(fontSize = 56, text = stringResource(R.string.try_out))
            Spacer(modifier = Modifier.height(100.dp))

            Row {
                ButtonRegistration(fontSize = 36, text = stringResource(R.string.login)) {}
                Spacer(modifier = Modifier.width(100.dp))
                ButtonRegistration(fontSize = 36, text = stringResource(R.string.sing_up)) {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    RegistrationScreen();
}
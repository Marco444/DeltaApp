package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                DeltaPreview();
            }
        }
    }
}

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
fun ButtonRegistration(fontSize: Int, text: String, handler: () -> Unit) {
    Button(onClick = handler, colors = ButtonDefaults.buttonColors(backgroundColor = Green)) {
        Text(text = text,
            fontSize = fontSize.sp,
        )
    }
}
@Composable
fun Registration() {
    Box {
        BackgroundImage(painter = painterResource(id = R.drawable.registration_background))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(300.dp))
            ButtonRegistration(fontSize = 36, text = "TRY OUT"){}
            Spacer(modifier = Modifier.height(100.dp))

            Row {
                ButtonRegistration(fontSize = 20, text = "Log In"){}
                Spacer(modifier = Modifier.width(100.dp))
                ButtonRegistration(fontSize = 20, text = "Sign Up"){}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeltaPreview() {
    Registration();
}
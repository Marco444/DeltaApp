package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.activities.mainactivity.UserViewModel
import com.example.myapplication.ui.theme.H1Font

@Composable
fun LoginButton(text: String, handler: () -> Unit) {
    Button(
        onClick = handler,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4B4B4B)),
        modifier = Modifier
            .width(220.dp)
            .height(60.dp)
    )
    {
        Text(
            text = text,
            color = Color(0xFFFFFFFF),
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun DrawerContent(
    userViewModel : UserViewModel,
    logoutRedirect: () -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xAF1F1F1F)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(0.4F)
        ) {
            Image(
                painter = painterResource(id = R.drawable.lionel_messi_20180626),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .scale(0.9F)
                    .clip(RoundedCornerShape(100.dp))
            )
        }
        Text(
            text = "Lionel Messi",
            color = Color(0xFFCFFFB3),
            fontSize = 30.sp,
            fontFamily = H1Font
        )

        LoginButton(text = "My profile", handler = {})
        LoginButton(text = "Logout", handler = logoutRedirect)

    }
}

package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
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

    userViewModel.getCurrentUser()

    val userState by  userViewModel.userState.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(0.4F)
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://media.geeksforgeeks.org/wp-content/uploads/20210101144014/gfglogo.png"),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .scale(0.9F)
                    .clip(RoundedCornerShape(100.dp))
            )
        }
        Text(
            text = userState.currentUser?.username ?: "Not logged in",
            style = MaterialTheme.typography.h2
        )

        if(userState.currentUser != null) {
            Text(text = "Last activity \n" + userState.currentUser!!.lastActivity.toString(),
                style = MaterialTheme.typography.body2, modifier = Modifier.align(Alignment.CenterHorizontally))
//            Text(text = userState.currentUser!!.email, style = MaterialTheme.typography.body2)
//            Row {
//                Text(text = userState.currentUser!!.firstName, style = MaterialTheme.typography.body2)
//                Text(text = userState.currentUser!!.lastName, style = MaterialTheme.typography.body2)
//            }
        }

        LoginButton(text = "Logout", handler = logoutRedirect)
    }
}

package com.example.myapplication.ui.components

import Base64BitMap
import Base64Image
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.activities.mainactivity.UserViewModel
import java.util.*





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
fun ProfilePicture(avatarUrl: String?) {
    if(avatarUrl != null) {
        val bitmap = Base64BitMap(avatarUrl)
        if(bitmap != null)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Profile Picture",
                modifier = Modifier.clip(CircleShape)
            )
    }
}

@Composable
fun DrawerContent(
    userViewModel : UserViewModel,
    logoutRedirect: () -> Unit
) {

    userViewModel.getCurrentUser()

    val userState by userViewModel.userState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        ProfilePicture(userState.currentUser?.avatarUrl)

        Text(
            text = userState.currentUser?.username ?: "Not logged in",
            style = MaterialTheme.typography.h2
        )

        if (userState.currentUser != null) {
            Text(
                text = "Last activity \n" + userState.currentUser!!.lastActivity.toString(),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        LoginButton(text = "Logout", handler = logoutRedirect)
    }
}

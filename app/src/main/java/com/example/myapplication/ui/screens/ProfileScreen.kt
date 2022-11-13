package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.theme.H1Font
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel


@Composable
fun ProfileScreen(viewModel: RoutinesViewModel){
    Box(modifier = Modifier
        .background(Color(0xFF1E1E1E))
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Button(
                //onClick = backButton,
                onClick = {},
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Green),
                modifier = Modifier
                    .padding(start = 18.dp, top = 15.dp)
                    .size(55.dp)
            )
            {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "content description",
                    tint = Color.Black
                )
            }
            Image(
                //painter = rememberAsyncImagePainter("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Lionel_Messi_20180626.jpg/640px-Lionel_Messi_20180626.jpg"),
                painter = painterResource(id = R.drawable.lionel_messi_20180626),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .scale(0.8F)
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(310.dp))
            )
            Text(
                text = "Lionel Messi",
                color = Color(0xFFCFFFB3),
                modifier = Modifier.padding(50.dp).align(Alignment.CenterHorizontally),
                fontSize = 50.sp,
                fontFamily = H1Font
            )
            Button(
                //onClick = backButton,
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4B4B4B)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(220.dp)
                    .height(60.dp)
                    .padding(5.dp)
            )
            {
                Text(text = "My Profile", color = Color(0xFFFFFFFF), modifier = Modifier.padding(5.dp))
            }
            Button(
                //onClick = backButton,
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4B4B4B)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(220.dp)
                    .height(60.dp)
                    .padding(5.dp)
            )
            {
                Text(text = "Settings", color = Color(0xFFFFFFFF), modifier = Modifier.padding(5.dp))
            }
            Button(
                //onClick = backButton,
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4B4B4B)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(220.dp)
                    .height(60.dp)
                    .padding(5.dp)
            )
            {
                Text(text = "Log Out", color = Color(0xFFFFFFFF), modifier = Modifier.padding(5.dp))
            }
        }
    }
}

@Preview
@Composable
fun RunProfileScreen(){
    ProfileScreen(viewModel = RoutinesViewModel())
}
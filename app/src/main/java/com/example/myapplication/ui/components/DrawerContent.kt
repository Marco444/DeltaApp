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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.activities.mainactivity.UserViewModel
import com.example.myapplication.ui.theme.H1Font

@Composable
fun DrawerContent(
    userViewModel : UserViewModel
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xAF1F1F1F))
    ) {
        Column() {
            Spacer(modifier = Modifier.height(70.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
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
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Lionel Messi",
                    color = Color(0xFFCFFFB3),
                    fontSize = 30.sp,
                    fontFamily = H1Font
                )
            }

            Spacer(modifier = Modifier.height(70.dp))
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
                Text(
                    text = "My Profile",
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
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
                Text(
                    text = "Settings",
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.padding(5.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                //onClick = backButton,
                onClick = {
                    userViewModel.logout()},
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4B4B4B)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(220.dp)
                    .height(60.dp)
                    .padding(5.dp)
            )
            {
                Text(
                    text = "Log Out",
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.padding(5.dp)
                )
            }

        }
    }
}
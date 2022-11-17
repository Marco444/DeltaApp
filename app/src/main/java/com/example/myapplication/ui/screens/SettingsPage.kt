package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel
import com.example.myapplication.ui.theme.*

@Composable
fun SettingsPage(
    viewModel: RoutinesViewModel,
    backHandler: () -> Unit
){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(backGround),
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(0.95F)
        ) {
            LazyColumn(){
                item {
                    SettingsCard(name = "Display Routine Images", description = "Decide whether to display or not the picture of a Routine", action = {})
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item {
                    SettingsCard(name = "Display Routine Images", description = "Decide whether to display or not the picture of a Routine", action = {})
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item {
                    SettingsCard(name = "Display Routine Images", description = "Decide whether to display or not the picture of a Routine", action = {})
                }
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

    }

}

@Composable
fun SettingsCard(
    name: String,
    description: String,
    action: () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Gray, RoundedCornerShape(30.dp))
    ) {
        Row(
            modifier = Modifier.padding(30.dp)
        ) {
            Switch(
                checked = true,
                onCheckedChange = {},
            )
            Spacer(modifier = Modifier.width(30.dp))
            Column() {
                Text(
                    text = name,
                    fontFamily = H1Font,
                    fontSize = 22.sp,
                    color = Green
                )
                Text(
                    text = description,
                    fontFamily = NormalFont,
                    fontSize = 15.sp,
                    color = Color.White
                )

            }
        }
    }

}



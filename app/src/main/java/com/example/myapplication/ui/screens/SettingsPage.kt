package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel
import com.example.myapplication.ui.components.BackButton
import com.example.myapplication.ui.theme.*
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun SettingsPage(
    viewModel: RoutinesViewModel,
    backHandler: () -> Unit
){

    val displayRoutineImages by viewModel.displayRoutineImages.collectAsState()
    val executionRoutineModeLite by viewModel.executionRoutineModeLite.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(backGround),
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            BackButton (handler = backHandler)

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(id = com.example.myapplication.R.string.settings),
                        style = MaterialTheme.typography.h1
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SettingsCard(
                        checked = displayRoutineImages,
                        name = stringResource(com.example.myapplication.R.string.display_routines_pic_title),
                        description = stringResource(com.example.myapplication.R.string.display_routines_pic_description),
                        action = {
                            viewModel.setDisplayRoutineImages()
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SettingsCard(
                        checked = executionRoutineModeLite,
                        name = stringResource(com.example.myapplication.R.string.exec_routine_mode_title),
                        description = stringResource(com.example.myapplication.R.string.exec_routine_mode_description),
                        action = {
                            viewModel.setExecutionRoutineModeLite()
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
            }

        }

    }

}

@Composable
fun SettingsCard(
    checked: Boolean,
    name: String,
    description: String,
    action: (Boolean) -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth(0.9F)
            .background(Gray, RoundedCornerShape(30.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = checked,
                onCheckedChange = action,
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



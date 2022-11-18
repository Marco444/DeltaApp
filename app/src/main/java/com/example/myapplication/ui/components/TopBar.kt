package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    scaffoldState: ScaffoldState,
    settingsRedirect: () -> Unit,
    hamburguerDisplay: Boolean
){
    val coroutineScope = rememberCoroutineScope()
    if(hamburguerDisplay) {
        Row(
            modifier = Modifier.padding(top = 10.dp, start = 10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            HamburgerButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
            SettingsButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = settingsRedirect
            )
        }
    } else {
       Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
           SettingsButton(
               onClick = settingsRedirect,
               modifier = Modifier
           )
       }
    }
}
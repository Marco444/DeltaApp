package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.ui.components.*
import com.example.myapplication.ui.navigation.NavBarScreen
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel
import kotlinx.coroutines.launch

@Composable
fun ProgressScreen(viewModel: RoutinesViewModel, actionRedirect: (Int) -> Unit, scaffoldState: ScaffoldState, errorRedirect: () -> Unit) {

    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.align(Alignment.Start)) {

            Row(modifier = Modifier.padding(top = 10.dp, start = 10.dp)) {
                HamburgerButton(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }

                )
            }
        }
        Row {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Text(
                    text = stringResource(R.string.progress_title),
                    style = MaterialTheme.typography.h1
                )


                Spacer(modifier = Modifier.height(10.dp))

                RoutineCardSortButton(viewModel = viewModel, NavBarScreen.Progress)

                Spacer(modifier = Modifier.height(20.dp))

                RoutinesGrid(
                    viewModel = viewModel,
                    actionRedirect = actionRedirect,
                    routineCard = RoutineCard.Progress,
                    buttonText = stringResource(id = R.string.see_progress),
                    errorRedirect = errorRedirect
                )
            }
        }
    }
}

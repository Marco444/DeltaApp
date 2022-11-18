package com.example.myapplication.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.components.*
import com.example.myapplication.ui.navigation.NavBarScreen
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel
import kotlinx.coroutines.launch

@Composable
fun RoutinesScreen(viewModel: RoutinesViewModel,
                   actionRedirect: (Int) -> Unit,
                   scaffoldState: ScaffoldState,
                   errorRedirect: () -> Unit,
                   settingsRedirect: () -> Unit
){

    val error by viewModel.error.collectAsState()
    val fetchState by viewModel.fetchingState.collectAsState()

    if(error) {
        errorRedirect()
        viewModel.errorHandled()
    }


    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background),

        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Column(modifier = Modifier.align(Alignment.Start)) {
            TopBar(scaffoldState, settingsRedirect, hamburguerDisplay = viewModel.cardsExpandable())
        }
        Row {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(R.string.routines_title),
                    style = MaterialTheme.typography.h1
                )


                Spacer(modifier = Modifier.height(10.dp))
                RoutineCardSortButton(viewModel = viewModel, screen = NavBarScreen.Routines)
                Spacer(modifier = Modifier.height(20.dp))

                RoutinesGrid(
                    viewModel = viewModel,
                    actionRedirect = actionRedirect,
                    routineCard = RoutineCard.MyRoutine,
                    buttonText = stringResource(id = R.string.Start),
                    errorRedirect = errorRedirect
                )
            }
        }
    }
    if(fetchState.isFetching){
        SimpleCircularProgressComponent()
    }
}
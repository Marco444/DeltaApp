package com.example.myapplication.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass

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
import com.example.myapplication.ui.activities.secondactivity.SecondActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun RoutinesScreen(viewModel: RoutinesViewModel,
                   actionRedirect: (Int) -> Unit,
                   scaffoldState: ScaffoldState,
                   errorRedirect: () -> Unit,
                   settingsRedirect: () -> Unit,
){

    val error by viewModel.error.collectAsState()
    val fetchState by viewModel.fetchingState.collectAsState()
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }
    val displayHamburguer by viewModel.hamburguer.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background),

        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Column(modifier = Modifier.align(Alignment.Start)) {
            TopBar(scaffoldState, settingsRedirect, hamburguerDisplay = displayHamburguer)
        }
        Row {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                    Text(
                        text = stringResource(R.string.routines_title),
                        style = MaterialTheme.typography.h2
                    )



                Spacer(modifier = Modifier.height(10.dp))
                RoutineCardSortButton(viewModel = viewModel, screen = NavBarScreen.Routines)
                Spacer(modifier = Modifier.height(20.dp))

                RoutinesGrid(
                    viewModel = viewModel,
                    actionRedirect = actionRedirect,
                    routineCard = RoutineCard.MyRoutine,
                    buttonText = stringResource(id = R.string.Start),
                    errorRedirect = errorRedirect,
                    refreshFunction = {viewModel.getUserRoutines()}

                )
            }
        }
    }
    if(fetchState.isFetching){
        SimpleCircularProgressComponent()
    }

    LaunchedEffect(key1 = fetchState.error, block = {
        if(fetchState.error)
            setSnackBarState(true)
    })
    // The Snackbar
    if (snackbarVisibleState) {
        Snackbar(
            action = {
                Button(
                    onClick = { setSnackBarState(!snackbarVisibleState); viewModel.getExploreRoutines() ; viewModel.getUserRoutines() },
                ) {
                    Text(text = "Try again")
                }
            },
            modifier = Modifier.padding(8.dp)

        ) { Text(text = fetchState.message) }
    }
}
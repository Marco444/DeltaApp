package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
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
fun ProgressScreen(viewModel: RoutinesViewModel,
                   actionRedirect: (Int) -> Unit,
                   scaffoldState: ScaffoldState,
                   errorRedirect: () -> Unit,
                   settingsRedirect: () -> Unit
)
{

    val coroutineScope = rememberCoroutineScope()
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }
    val fetchState by viewModel.fetchingState.collectAsState()

    val displayHamburguer by viewModel.hamburguer.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.align(Alignment.Start)) {
            TopBar(scaffoldState, settingsRedirect, hamburguerDisplay = displayHamburguer)
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
                    errorRedirect = errorRedirect,
                    refreshFunction = {viewModel.getUserRoutines()}
                )
            }
        }
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
                    onClick = { setSnackBarState(!snackbarVisibleState); viewModel.getUserRoutines() },
                ) {
                    Text(text = "Try again")
                }
            },
            modifier = Modifier.padding(8.dp)

        ) { Text(text = fetchState.message) }
    }
}

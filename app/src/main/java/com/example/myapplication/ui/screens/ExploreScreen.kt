package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.components.*
import com.example.myapplication.ui.navigation.NavBarScreen
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel
import com.example.myapplication.ui.theme.Gray
import com.example.myapplication.ui.theme.Green
import kotlinx.coroutines.launch





@Composable
fun ExploreScreen(viewModel: RoutinesViewModel,
                  scaffoldState: ScaffoldState,
                  actionRedirect: (Int) -> Unit,
                  refferedRoutineId: Int = -1,
                  errorRedirect: () -> Unit,
                  settingsRedirect: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val fetchState by viewModel.fetchingState.collectAsState()
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }

    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(modifier = Modifier.align(Alignment.Start)) {
            TopBar(scaffoldState, settingsRedirect, hamburguerDisplay = viewModel.cardsExpandable())
        }
        Row {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.explore_title),
                    style = MaterialTheme.typography.h1
                )

                if(refferedRoutineId != -1) {
                    Text(text = "This references to $refferedRoutineId", color = White)
                }
                Spacer(modifier = Modifier.height(10.dp))

                SearchAndFilter(viewModel = viewModel)

                Spacer(modifier = Modifier.height(20.dp))

                RoutinesGrid(
                    viewModel = viewModel,
                    actionRedirect = actionRedirect,
                    routineCard = RoutineCard.ExploreRoutine,
                    buttonText = stringResource(id = R.string.Preview),
                    errorRedirect = errorRedirect
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


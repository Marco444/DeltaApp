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
fun SearchField(viewModel: RoutinesViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current
    TextField(
        value = text,
        onValueChange = { newText ->
            if (newText.text.isNotEmpty() && newText.text.last() == '\n'){
                focusManager.clearFocus()
                if(text.text.length >  3)
                    viewModel.getExploreWithParams(text.text)
            }else{
                text = newText
            }
            if(newText.text.isEmpty()){
                viewModel.getExploreWithParams(null)
            }
        },
        shape = RoundedCornerShape(8.dp),
        trailingIcon = {
            IconButton(onClick = {
                if(text.text.length >= 3)
                    viewModel.getExploreWithParams(text.text)
                else
                    viewModel.getExploreWithParams(null)

            }) {
                Icon(Icons.Filled.Search, "")
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = White,
            focusedIndicatorColor =  Color.Transparent),
        textStyle = TextStyle.Default.copy(fontSize = 15.sp),
        modifier = Modifier
            .height(55.dp)
            .fillMaxWidth(0.75F)
    )
}

@Composable
fun FilterButton(viewModel: RoutinesViewModel) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(
            onClick = { expanded = !expanded},
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(White)
                .height(40.dp)
                .fillMaxWidth(0.65F)
        ) {
            Icon(Icons.Filled.Sort, contentDescription = null)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
            .background(
                Color.White
            )
            .clip(RoundedCornerShape(8.dp))) {
            for(sortOption in SortOption.values()) {
                DropdownMenuItem(onClick = { viewModel.sortRoutines(sortOption, NavBarScreen.Explore) ; expanded = false }) {
                    when (sortOption) {
                        SortOption.FAVOURITE -> Text(text = stringResource(id = R.string.favourite))
                        SortOption.DATE -> Text(text = stringResource(id = R.string.date))
                        else -> Text(text = stringResource(id = R.string.points))
                    }
                }
            }
        }
    }
}

@Composable
fun SearchAndFilter(viewModel: RoutinesViewModel) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        SearchField(viewModel)
        Spacer(Modifier.width(10.dp))
        FilterButton(viewModel = viewModel)
    }
}

@Composable
fun ExploreScreen(viewModel: RoutinesViewModel, scaffoldState: ScaffoldState, actionRedirect: (Int) -> Unit, refferedRoutineId: Int = -1, errorRedirect: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()

    val error by viewModel.error.collectAsState()
    if(error) {
        errorRedirect()
        viewModel.errorHandled()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(modifier = Modifier.align(Alignment.Start)) {
            TopBar(scaffoldState)
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
}


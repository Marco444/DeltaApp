package com.example.myapplication.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.R
import com.example.myapplication.ui.components.*
import com.example.myapplication.ui.navigation.NavBarScreen
import com.example.myapplication.viewmodel.RoutinesViewModel
import kotlinx.coroutines.launch


@Composable
fun SearchField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        },
        shape = RoundedCornerShape(8.dp),
        trailingIcon = {
            Icon(Icons.Filled.Search, "")
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = White,
        focusedIndicatorColor =  Color.Transparent),
        textStyle = TextStyle.Default.copy(fontSize = 15.sp)
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
                    Text(text = sortOption.name)
                }
            }
        }
    }}

@Composable
fun SearchAndFilter(viewModel: RoutinesViewModel) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            SearchField()
            Spacer(Modifier.width(10.dp))
            FilterButton(viewModel = viewModel)
    }
}

@Composable
fun ExploreScreen(viewModel: RoutinesViewModel, scaffoldState: ScaffoldState){
    val coroutineScope = rememberCoroutineScope()
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(20.dp))
        Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){
            HamburgerButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
        )
            Text(
                text = stringResource(R.string.explore_title),
                style = MaterialTheme.typography.h1
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        SearchAndFilter(viewModel = viewModel);

        Spacer(modifier = Modifier.height(20.dp))

       RoutinesGrid(viewModel = viewModel, actionRedirect = { }, routineCard = RoutineCard.ExploreRoutine)
    }
}

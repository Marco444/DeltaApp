package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.components.RoutineCard
import com.example.myapplication.viewmodel.RoutinesViewModel
import com.example.myapplication.viewmodel.RoutineCardAction


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
fun SearchAndFilter() {
    Row (verticalAlignment = Alignment.CenterVertically){
        SearchField()
        Spacer(Modifier.width(10.dp))
        IconButton(
            onClick = { },
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(White)     ) {
            Icon(Icons.Filled.Edit, contentDescription = null)
        }
    }}
@Composable
fun ExploreScreen(viewModel: RoutinesViewModel){
    Column (
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.explore_title),
            style = MaterialTheme.typography.h1
        )

        Spacer(modifier = Modifier.height(10.dp))

        SearchAndFilter();

        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
           items(items = viewModel.uiState.value.exploreRoutines.values.toTypedArray()) {
               RoutineCard(
                   routine = it,
                   iconId = if(viewModel.isAddedRoutine(it.id))
                                R.drawable.check_circle_white_24dp
                            else
                                R.drawable.add_white_24dp,
                   clickedIcon = { viewModel.addedRoutineFromExplore(it.id) },
                   action = RoutineCardAction.Explore
               )
            }
        }
    }
}

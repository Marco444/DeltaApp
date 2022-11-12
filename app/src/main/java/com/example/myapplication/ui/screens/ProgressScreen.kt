package com.example.myapplication.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.data.Routines
import com.example.myapplication.ui.components.ROUTINE_CARD_WIDTH
import com.example.myapplication.ui.components.RoutineCard
import com.example.myapplication.ui.components.RoutineCardSortButton
import com.example.myapplication.ui.components.RoutinesGrid
import com.example.myapplication.ui.navigation.NavBarScreen
import com.example.myapplication.ui.navigation.Screen
import com.example.myapplication.viewmodel.RoutinesViewModel

fun comparebyFavourite(routine1: Routines, routine2: Routines): Int {
    return routine1.id - routine2.id
}

@Composable
fun ProgressScreen(viewModel: RoutinesViewModel, actionRedirect: (Int) -> Unit){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.progress_title),
            style = MaterialTheme.typography.h1,
        )

        Spacer(modifier = Modifier.height(10.dp))

        RoutineCardSortButton(
          viewModel = viewModel
        )

        Spacer(modifier = Modifier.height(20.dp))

        RoutinesGrid(viewModel = viewModel, actionRedirect = actionRedirect, routineCard = RoutineCard.Progress)
    }
}

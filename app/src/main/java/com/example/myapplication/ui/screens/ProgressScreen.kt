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
import com.example.myapplication.ui.components.ROUTINE_CARD_WIDTH
import com.example.myapplication.ui.components.RoutineCard
import com.example.myapplication.ui.components.RoutineCardSortButton
import com.example.myapplication.ui.components.RoutinesGrid
import com.example.myapplication.viewmodel.RoutinesViewModel
import com.example.myapplication.viewmodel.RoutineCardAction

@Composable
fun ProgressScreen(viewModel: RoutinesViewModel){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.progress_title),
            style = MaterialTheme.typography.h1,
        )

        Spacer(modifier = Modifier.height(10.dp))

        RoutineCardSortButton()

        Spacer(modifier = Modifier.height(20.dp))

        RoutinesGrid(viewModel = viewModel, actionRedirect = { }, routineCard = RoutineCard.MyRoutine)
    }
}

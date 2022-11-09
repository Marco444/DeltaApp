package com.example.myapplication.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.viewmodel.RoutinesViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoutinesGrid(viewModel: RoutinesViewModel, actionRedirect: () -> Unit, routineCard: RoutineCard) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(ROUTINE_CARD_WIDTH.dp),
        content = {
            items(viewModel.uiState.value.exploreRoutines.values.toTypedArray()) { routine ->
                Box(
                    modifier= Modifier.fillMaxSize().padding(bottom = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    RoutineCard(
                        routine = routine,
                        iconId =    if (!viewModel.isAddedRoutine(routine.id))
                                        routineCard.iconUnClicked
                                    else
                                        routineCard.iconClicked,
                        clickedIcon = { viewModel.addedRoutineFromExplore(routine.id) },
                        routineCard = routineCard,
                        viewModel = viewModel,
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        })
}
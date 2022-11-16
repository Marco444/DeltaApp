package com.example.myapplication.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel

@Composable
fun RoutinesGrid(viewModel: RoutinesViewModel, actionRedirect: (Int) -> Unit, routineCard: RoutineCard, buttonText: String) {
    val hasNextPage by viewModel.hasNextPage.collectAsState()
    LazyVerticalGrid(
        GridCells.Adaptive(ROUTINE_CARD_WIDTH.dp)) {
        items(viewModel.getRoutines(routineCard)) { routineState ->
            Box(
                modifier= Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {

                //this line literally makes the recomposition happen!
                val routine by routineState.collectAsState()

                RoutineCard(
                    routine = routine,
                    iconId =    if (!viewModel.isSelected(routine.id, routineCard))
                        routineCard.iconUnClicked
                    else
                        routineCard.iconClicked,
                    clickedIcon = { viewModel.clickedIcon(routine.id) },
                    routineCard = routineCard,
                    buttonText = buttonText,
                    viewModel = viewModel,
                    actionHandler = { actionRedirect(routine.id) }
                )

                Spacer(modifier = Modifier.height(30.dp))
            }
        }

        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(hasNextPage) {
                    Button1(
                        fontSize = 17,
                        text = stringResource(id = R.string.load_more),
                        handler = { viewModel.nextPage() }
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
        }

    }
}
package com.example.myapplication.ui.screens

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.components.RoutineCard
import com.example.myapplication.ui.components.RoutineCardSortButton
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.viewmodel.DeltaViewModel

@Composable
fun ProgressScreen(viewModel: DeltaViewModel){
    Column (
        modifier = Modifier.background(androidx.compose.ui.graphics.Color.Black),
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
        LazyColumn {
//            items(items = viewModel.uiState.value.exploreRoutines.values.toTypedArray()) {
//                RoutineCard(
//                    routine = it,
//                    iconId = if(viewModel.isAddedRoutine(it.id))
//                        R.drawable.check_circle_white_24dp
//                    else
//                        R.drawable.add_white_24dp,
//                    clickedIcon = { viewModel.addedRoutineFromExplore(it.id) }
//                )
//            }
        }
    }
}

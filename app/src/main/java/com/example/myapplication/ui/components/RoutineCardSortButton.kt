package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.Routines
import com.example.myapplication.viewmodel.RoutinesViewModel

@Composable
fun RoutineCardSortButton(favoriteHandler: () -> Unit, dateSortHandler: () -> Unit, pointsSortHandler: () -> Unit) {
    Row (
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Gray)
            .padding(5.dp),
        ){
        SortButton(onClick = favoriteHandler, txt = stringResource(R.string.favourite))
        Delimiter()
        SortButton(onClick = dateSortHandler, txt = stringResource(R.string.date))
        Delimiter()
        SortButton(onClick = pointsSortHandler, txt = stringResource(R.string.points))
    }
}

@Composable
fun Delimiter() {
    Text(
        text = " | ",
        fontSize = 25.sp,
        color = Color.White,
        modifier = Modifier.padding(6.dp),
    )
}

@Composable
fun SortButton(onClick: () -> Unit, txt: String) {
    Box(Modifier.clickable(onClick = onClick)) {
        Text(
            text = txt,
            fontSize = 25.sp,
            color = Color.White,
            modifier = Modifier.padding(6.dp),
        )
    }
}

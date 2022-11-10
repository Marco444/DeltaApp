package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.Routines
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.viewmodel.RoutinesViewModel

enum class SortOption {
    FAVOURITE, DATE, POINTS;

    fun color(selected: SortOption): Color {
        return if(selected.name == this.name) Black else Gray
    }
}

@Composable
fun RoutineCardSortButton(favoriteHandler: () -> Unit, dateSortHandler: () -> Unit, pointsSortHandler: () -> Unit) {
    var selected by remember { mutableStateOf(SortOption.FAVOURITE) }

    Row (
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Gray)
            .padding(5.dp),
        ){
        SortButton(onClick = {favoriteHandler(); selected = SortOption.FAVOURITE}, txt = stringResource(R.string.favourite), color = SortOption.FAVOURITE.color(selected))
        Delimiter()
        SortButton(onClick = {dateSortHandler(); selected = SortOption.DATE}, txt = stringResource(R.string.date), color = SortOption.DATE.color(selected))
        Delimiter()
        SortButton(onClick = {pointsSortHandler(); selected = SortOption.POINTS}, txt = stringResource(R.string.points), color = SortOption.POINTS.color(selected))
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
fun SortButton(onClick: () -> Unit, txt: String, color: Color) {
    Box(Modifier.clickable(onClick = onClick).padding(10.dp)) {
        Text(
            text = txt,
            fontSize = 25.sp,
            color = White,
            modifier = Modifier.clip(RoundedCornerShape(5.dp)).background(color),
        )
    }
}

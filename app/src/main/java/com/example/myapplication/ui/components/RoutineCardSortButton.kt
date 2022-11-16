package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.navigation.NavBarScreen
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel
import com.example.myapplication.ui.classes.Routines
import kotlinx.coroutines.flow.MutableStateFlow

enum class SortOption {
    FAVOURITE {
        override val comparator: (MutableStateFlow<Routines>, MutableStateFlow<Routines>) -> Int
            get() = {a: MutableStateFlow<Routines>, b: MutableStateFlow<Routines> -> b.value.favourite.compareTo(a.value.favourite) }
    },
    DATE {
        override val comparator: (MutableStateFlow<Routines>, MutableStateFlow<Routines>) -> Int
            get() = {a: MutableStateFlow<Routines>, b: MutableStateFlow<Routines> -> b.value.id - a.value.id}
    },
    POINTS() {
        override val comparator: (MutableStateFlow<Routines>, MutableStateFlow<Routines>) -> Int
            get() = {a: MutableStateFlow<Routines>, b: MutableStateFlow<Routines> -> b.value.points.value - a.value.points.value}
    };

    abstract val comparator: (MutableStateFlow<Routines>, MutableStateFlow<Routines>) -> Int

    fun color(selected: SortOption): Color {
        return if(selected.name == this.name) Black else Gray
    }
}

@Composable
fun RoutineCardSortButton(viewModel: RoutinesViewModel, screen: NavBarScreen) {

    val selected by viewModel.getSortState().collectAsState()

    Row (
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Gray)
            .padding(5.dp)
            .height(30.dp),
        ){

        for (sortOption in SortOption.values()) {
            SortButton(onClick = {viewModel.setSortState(sortOption, screen)}, txt = sortOption.name, color = sortOption.color(selected) )
            if(sortOption != SortOption.POINTS) Delimiter()
        }

    }
}

@Composable
fun Delimiter() {
    Text(
        text = " | ",
        fontSize = 25.sp,
        color = White,
        modifier = Modifier.padding(6.dp),
    )
}

@Composable
fun SortButton(onClick: () -> Unit, txt: String, color: Color) {
    Box(
        Modifier
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .padding(5.dp)

    ) {
        Text(
            text = txt,
            fontSize = 15.sp,
            color = White,
            modifier = Modifier
                .background(color),
        )
    }
}

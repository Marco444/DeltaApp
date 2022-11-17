package com.example.myapplication.ui.activities.secondactivity


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.R
import com.example.myapplication.ui.classes.Routines
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File
import java.util.*


class RoutinesState {

    var userRoutines by mutableStateOf(emptyList<MutableStateFlow<Routines>>())

    var exploreRoutines by mutableStateOf((emptyList<MutableStateFlow<Routines>>()))

}
data class PagedRoutines(
    val content : List<Routines>,
    val page : Int,
    val isLastPage : Boolean
)
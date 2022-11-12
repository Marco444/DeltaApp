package com.example.myapplication.viewmodel

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Exercise
import com.example.myapplication.data.Routines
import com.example.myapplication.ui.components.RoutineCard
import com.example.myapplication.ui.components.SortOption
import com.example.myapplication.ui.navigation.NavBarScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class RoutinesViewModel : ViewModel() {
    private val _sortState = MutableStateFlow(SortOption.POINTS)

    private val _routinesState = MutableStateFlow(RoutinesState())

    fun getSortState(): MutableStateFlow<SortOption> {
        return _sortState
    }
    fun setSortState(option: SortOption, screen: NavBarScreen) {
        _sortState.value = option
        sortRoutines(option, screen)

    }
    fun sortRoutines(option: SortOption, screen: NavBarScreen) {
        if(option == SortOption.POINTS)
            sortRoutinesPoints(screen)
        if(option == SortOption.FAVOURITE)
            sortRoutinesFavourite(screen)
        if(option == SortOption.DATE)
            sortRoutinesDate(screen)
    }

    fun getRoutines(routineCard: RoutineCard): List<MutableStateFlow<Routines>> {
        return if(routineCard == RoutineCard.ExploreRoutine) _routinesState.value.exploreRoutines
        else _routinesState.value.userRoutines
    }

    fun routine(id: Int): Routines {
        return _routinesState.value.userRoutines.find { routine ->routine.value.id == id }!!.value
    }

    fun sortRoutinesDate(screen: NavBarScreen) {
        if(screen == NavBarScreen.Explore)
            _routinesState.value.exploreRoutines = _routinesState.value.exploreRoutines.sortedBy { routine -> routine.value.id }
        else
            _routinesState.value.userRoutines = _routinesState.value.userRoutines.sortedBy { routine -> routine.value.id }
    }

    fun sortRoutinesFavourite(screen: NavBarScreen) {
        if(screen == NavBarScreen.Explore)
            _routinesState.value.exploreRoutines =  _routinesState.value.exploreRoutines.sortedBy { routine -> !routine.value.favourite}
        else
            _routinesState.value.userRoutines = _routinesState.value.userRoutines.sortedBy { routine -> !routine.value.favourite }
    }

    fun sortRoutinesPoints(screen: NavBarScreen) {
        if(screen == NavBarScreen.Explore)
            _routinesState.value.exploreRoutines =  _routinesState.value.exploreRoutines.sortedBy { routine -> routine.value.points}
        else
            _routinesState.value.userRoutines =  _routinesState.value.userRoutines.sortedBy { routine -> routine.value.points}
    }

    fun clickedIcon(id: Int, routineCard: RoutineCard) {
        if(RoutineCard.ExploreRoutine == routineCard) {
           val routine = _routinesState.value.exploreRoutines.find { routine ->routine.value.id == id }!!
           routine.update { it.copy(added = !it.added) }
            _routinesState.value.userRoutines =  _routinesState.value.userRoutines + routine
        } else {
            val routine = _routinesState.value.userRoutines.find { routine ->routine.value.id == id }!!
            routine.update { it.copy(favourite = !it.favourite) }
        }

    }

    fun isSelected(id: Int, routineCard: RoutineCard): Boolean {
        return if(RoutineCard.ExploreRoutine == routineCard)
            _routinesState.value.exploreRoutines.find { routine ->routine.value.id == id }!!.value.added
        else
            _routinesState.value.userRoutines.find { routine ->routine.value.id == id }!!.value.favourite
    }


    private var screenWidth: WindowWidthSizeClass = WindowWidthSizeClass.Compact

    fun cardsExpandable(): Boolean {
        return screenWidth != WindowWidthSizeClass.Expanded
    }

    fun setWidth(width: WindowWidthSizeClass) {
        screenWidth = width;
    }


    private var actualOpinion: Int = 2

    fun getActualOpinion(): Int {
        return actualOpinion
    }

    fun upOpinion() {
        if(actualOpinion < 4)
            actualOpinion++
    }

    fun downOpinion() {
        if(actualOpinion > 0)
            actualOpinion--
    }


}
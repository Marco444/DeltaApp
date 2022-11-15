package com.example.myapplication.ui.activities.secondactivity

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.data.repository.RoutinesRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.ui.components.RoutineCard
import com.example.myapplication.ui.components.SortOption
import com.example.myapplication.ui.navigation.NavBarScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoutinesViewModel(
    routinesRepository: RoutinesRepository,
    userRepository: UserRepository
) : ViewModel() {
    private val _sortState = MutableStateFlow(SortOption.POINTS)

    private val _routinesState = MutableStateFlow(RoutinesState())

    init {
        viewModelScope.launch {
          val aux = userRepository.getCurrentUser(false)
           if(aux != null)
               aux.id?.let {
                  _routinesState.value.userRoutines = userRepository.getUserRoutine(true, it).map {routine -> MutableStateFlow(routine) } }
        }
        viewModelScope.launch {
            _routinesState.value.exploreRoutines =
                routinesRepository.getRoutines(true).map { MutableStateFlow(it) }
        }
    }
    fun getSortState(): MutableStateFlow<SortOption> {
        return _sortState
    }
    fun setSortState(option: SortOption, screen: NavBarScreen) {
        _sortState.value = option
        sortRoutines(option, screen)

    }
    fun sortRoutines(option: SortOption, screen: NavBarScreen) {
        println("sorting with ")
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

    private fun sortRoutinesDate(screen: NavBarScreen) {
        if(screen == NavBarScreen.Explore)
            _routinesState.value.exploreRoutines = _routinesState.value.exploreRoutines.sortedBy { routine -> -routine.value.id }
        else
            _routinesState.value.userRoutines = _routinesState.value.userRoutines.sortedBy { routine -> -routine.value.id }
    }

    private fun sortRoutinesFavourite(screen: NavBarScreen) {
        if(screen == NavBarScreen.Explore)
            _routinesState.value.exploreRoutines =  _routinesState.value.exploreRoutines.sortedBy { routine -> !routine.value.favourite}
        else
            _routinesState.value.userRoutines = _routinesState.value.userRoutines.sortedBy { routine -> !routine.value.favourite }
    }

    private fun sortRoutinesPoints(screen: NavBarScreen) {
        if(screen == NavBarScreen.Explore)
            _routinesState.value.exploreRoutines =  _routinesState.value.exploreRoutines.sortedBy { routine -> -routine.value.points.value}
        else
            _routinesState.value.userRoutines =  _routinesState.value.userRoutines.sortedBy { routine -> -routine.value.points.value}
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
        else {
            if(_routinesState.value.userRoutines.isNotEmpty()) {
                _routinesState.value.userRoutines.find { routine -> routine.value.id == id }!!.value.favourite
            }else
                return false
        }

    }


    private var screenWidth: WindowWidthSizeClass = WindowWidthSizeClass.Compact

    fun cardsExpandable(): Boolean {
        return screenWidth != WindowWidthSizeClass.Expanded
    }

    fun setWidth(width: WindowWidthSizeClass) {
        screenWidth = width;
    }


}
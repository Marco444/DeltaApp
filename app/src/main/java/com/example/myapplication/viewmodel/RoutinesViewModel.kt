package com.example.myapplication.viewmodel

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Exercise
import com.example.myapplication.data.Routines
import com.example.myapplication.ui.components.RoutineCard
import com.example.myapplication.ui.navigation.NavBarScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RoutinesViewModel : ViewModel() {


    private val _routinesState = MutableStateFlow(RoutinesState())
    val routinesState: StateFlow<RoutinesState> = _routinesState.asStateFlow()

    fun getRoutines(routineCard: RoutineCard): MutableList<Routines> {
        return if(routineCard == RoutineCard.ExploreRoutine) routinesState.value.exploreRoutines
        else routinesState.value.userRoutines
    }

    fun routine(id: Int): Routines? {
        return _routinesState.value.userRoutines.find { routine: Routines -> routine.id == id }
    }

    fun sortRoutinesDate(screen: NavBarScreen) {
       routinesState.value.exploreRoutines.sortBy { routines: Routines -> routines.id }
    }

    fun sortRoutinesFavourite(screen: NavBarScreen) {
        routinesState.value.exploreRoutines.sortBy { routines: Routines -> routines.favourite }
    }

    fun sortRoutinesPoints(screen: NavBarScreen) {
        _routinesState.value.exploreRoutines = _routinesState.value.exploreRoutines.sortedBy { it.points } as MutableList<Routines>
    }

    fun clickedIcon(id: Int, routineCard: RoutineCard) {
        if(RoutineCard.ExploreRoutine == routineCard)
            _routinesState.value.exploreRoutines.find { routine: Routines -> routine.id == id }!!.added = true
        else
            _routinesState.value.userRoutines
                .find { routine: Routines -> routine.id == id }!!.favourite = true
    }

    fun isSelected(id: Int, routineCard: RoutineCard): Boolean {
        return if(RoutineCard.ExploreRoutine == routineCard)
            _routinesState.value.exploreRoutines.find { routine: Routines -> routine.id == id }!!.added
        else
            _routinesState.value.userRoutines.find { routine: Routines -> routine.id == id }!!.favourite
    }


    //Should this go userViewModel??
    private var _loggedIn  = MutableStateFlow(false)

    fun login(username: String, password: String) {
        _loggedIn.update { true } ;
    }

    fun isLoggedIn(): Boolean {
        return _loggedIn.value
    }

    private var screenWidth: WindowWidthSizeClass = WindowWidthSizeClass.Compact

    fun cardsExpandable(): Boolean {
        return screenWidth != WindowWidthSizeClass.Expanded
    }

    fun setWidth(width: WindowWidthSizeClass) {
        screenWidth = width;
    }
    fun getRoutineWarmUpExercises(id:Int) : List<Exercise>{
        return   _routinesState.value.userRoutines.find { routine: Routines -> routine.id == id }!!.exercises.warmUpExercises
    }
    fun getRoutineCoolDownExercises(id:Int) : List<Exercise>{
        return   _routinesState.value.userRoutines.find { routine: Routines -> routine.id == id }!!.exercises.coolDownExercises
    }
    fun getRoutineMainSetExercises(id:Int) : List<Exercise>{
        return   _routinesState.value.userRoutines.find { routine: Routines -> routine.id == id }!!.exercises.mainSetExercises
    }
//    private var _exploreRoutines = mutableListOf<MutableStateFlow<RoutinesT>>()
//
//    fun getExploreRoutines(): List<RoutinesT> {
//        return _exploreRoutines.map{it.asStateFlow().value}
//    }
//
//    fun addedRoutineFromExplore(id: Int) {
//        _exploreRoutines[id].value.added = true;
//    }
//
//    fun isAddedRoutine(id: Int): Boolean {
//        return  _exploreRoutines[id].value.added;
//    }

}
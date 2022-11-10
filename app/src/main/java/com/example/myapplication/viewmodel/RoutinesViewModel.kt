package com.example.myapplication.viewmodel

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Routines
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RoutinesViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(RoutinesState())
    val uiState: StateFlow<RoutinesState> = _uiState.asStateFlow()


    fun routine(id: Int): Routines? {
        return uiState.value.exploreRoutines[id]
    }

    //Should this go userViewModel??
    private var _loggedIn  = MutableStateFlow(false)

    fun login(username: String, password: String) {
        _loggedIn.update { true } ;
    }

    fun isLoggedIn(): Boolean {
        return _loggedIn.value
    }

     var screenWidth: WindowWidthSizeClass = WindowWidthSizeClass.Compact
        private set

    fun cardsExpandable(): Boolean {
        return screenWidth != WindowWidthSizeClass.Expanded
    }


    fun setWidth(width: WindowWidthSizeClass) {
        screenWidth = width;
    }

    fun addedRoutineFromExplore(id: Int) {
        _uiState.value.exploreRoutines[id]?.added = !_uiState.value.exploreRoutines[id]?.added!!
    }

    fun isAddedRoutine(id: Int): Boolean {
        return _uiState.value.exploreRoutines[id]?.added ?: false;
    }

    fun executeRoutine(id: Int) {

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
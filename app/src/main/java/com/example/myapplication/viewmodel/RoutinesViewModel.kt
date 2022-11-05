package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RoutinesViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(RoutinesState())
    val uiState: StateFlow<RoutinesState> = _uiState.asStateFlow()


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
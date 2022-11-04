package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DeltaViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(DeltaUiState())
    val uiState: StateFlow<DeltaUiState> = _uiState.asStateFlow()

//    var exploreRoutines = mutableListOf<MutableStateFlow<RoutinesT>>()
//        private set
//
//    fun addedRoutineFromExplore(id: Int) {
//        exploreRoutines[id].value.added = true;
//    }
//
//    fun isAddedRoutine(id: Int): Boolean {
//        return  exploreRoutines[id].value.added;
//    }

    fun addedRoutineFromExplore(id: Int) {
        _uiState.value.exploreRoutines[id]?.added = !_uiState.value.exploreRoutines[id]?.added!!
    }

    fun isAddedRoutine(id: Int): Boolean {
        return _uiState.value.exploreRoutines[id]?.added ?: false;
    }

}
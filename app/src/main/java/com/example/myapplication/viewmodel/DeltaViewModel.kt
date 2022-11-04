package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DeltaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DeltaUiState())
    val uiState: StateFlow<DeltaUiState> = _uiState.asStateFlow()

}
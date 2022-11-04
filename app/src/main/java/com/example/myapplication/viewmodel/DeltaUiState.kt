package com.example.myapplication.viewmodel

import com.example.myapplication.R

data class RoutinesT (
    val id: Int,
    val img: Int,
    val description: String,
    val title: String,
    var added: Boolean,
)

class DeltaUiState {

    val username: String = ""

    val userRoutines = listOf<RoutinesT>()

    val exploreRoutines = mutableMapOf<Int, RoutinesT>()//mutableListOf<RoutinesT>()

    init {
        exploreRoutines[0] = RoutinesT(0, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[1] = RoutinesT(1, R.drawable.registration_background,
            "This is a sample routine text", "PULL", true)
    }
}
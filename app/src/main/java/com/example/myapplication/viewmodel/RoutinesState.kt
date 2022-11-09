package com.example.myapplication.viewmodel


import com.example.myapplication.R

data class RoutinesT (
    val id: Int,
    val img: Int,
    val description: String,
    val title: String,
    var added: Boolean,
)


class RoutinesState {


    val userRoutines = listOf<RoutinesT>()

    val exploreRoutines = mutableMapOf<Int, RoutinesT>()//mutableListOf<RoutinesT>()

    init {
        exploreRoutines[0] = RoutinesT(0, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[1] = RoutinesT(1, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
        exploreRoutines[2] = RoutinesT(2, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[3] = RoutinesT(3, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
        exploreRoutines[4] = RoutinesT(4, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[5] = RoutinesT(5, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
        exploreRoutines[6] = RoutinesT(6, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[7] = RoutinesT(7, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
    }
}
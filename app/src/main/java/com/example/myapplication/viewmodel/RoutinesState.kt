package com.example.myapplication.viewmodel


import com.example.myapplication.R
import com.example.myapplication.data.Routines


class UserRoutinesState {
    val exploreRoutines = mutableMapOf<Int, Routines>()//mutableListOf<RoutinesT>()

    init {
        exploreRoutines[0] = Routines(0, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[1] = Routines(1, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
        exploreRoutines[2] = Routines(2, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[3] = Routines(3, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
        exploreRoutines[4] = Routines(4, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[5] = Routines(5, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
        exploreRoutines[6] = Routines(6, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[7] = Routines(7, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
    }
}

class RoutinesState {


    val userRoutines = listOf<Routines>()

    val exploreRoutines = mutableMapOf<Int, Routines>()//mutableListOf<RoutinesT>()

    init {
        exploreRoutines[0] = Routines(0, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[1] = Routines(1, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
        exploreRoutines[2] = Routines(2, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[3] = Routines(3, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
        exploreRoutines[4] = Routines(4, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[5] = Routines(5, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
        exploreRoutines[6] = Routines(6, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)
        exploreRoutines[7] = Routines(7, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)
    }
}
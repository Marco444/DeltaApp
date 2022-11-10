package com.example.myapplication.viewmodel


import com.example.myapplication.R
import com.example.myapplication.data.RoutineExercises
import com.example.myapplication.data.Routines

class RoutinesState {


    val userRoutines = mutableListOf<Routines>()

    var exploreRoutines = mutableListOf<Routines>()//mutableListOf<RoutinesT>()

    init {
        exploreRoutines.add( Routines(0, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false))
        exploreRoutines.add( Routines(1, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false))
        exploreRoutines.add( Routines(2, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false))
        exploreRoutines.add( Routines(3, R.drawable.registration_background,
            "This is a sample routine text", "PULL 3", false))
        exploreRoutines.add( Routines(4, R.drawable.registration_background,
            "This is a sample routine text", "PUSH 4", false))
        exploreRoutines.add( Routines(5, R.drawable.registration_background,
            "This is a sample routine text", "PULL 5", false))
        exploreRoutines.add( Routines(6, R.drawable.registration_background,
            "This is a sample routine text", "PUSH 6", false))
        exploreRoutines.add( Routines(7, R.drawable.registration_background,
            "This is a sample routine text", "PULL 7", false))

        userRoutines.add( Routines(8, R.drawable.registration_background,
            "This is a sample routine text", "MY PUSH 8", false, points = 3))
        userRoutines.add( Routines(9, R.drawable.registration_background,
            "This is a sample routine text", "MY PULL 9", false, points = 10))
        userRoutines.add( Routines(10, R.drawable.registration_background,
            "This is a sample routine text", "MY PUSH 10", false, points = 1))
      userRoutines.add( Routines(11, R.drawable.registration_background,
            "This is a sample routine text", "MY PULL 11", false))
    }
}
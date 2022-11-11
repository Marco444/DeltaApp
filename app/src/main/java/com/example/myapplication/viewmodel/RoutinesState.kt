package com.example.myapplication.viewmodel


import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.R
import com.example.myapplication.data.Exercise
import com.example.myapplication.data.RoutineExercises
import com.example.myapplication.data.Routines
import kotlinx.coroutines.flow.MutableStateFlow

class RoutinesState {


    var userRoutines = mutableListOf<MutableStateFlow<Routines>>()
    var exploreRoutines = mutableListOf<MutableStateFlow<Routines>>()

    var actualExercise = mutableStateOf(Exercise(0,0,0,0,0,"AA","AAA"))

    init {
        exploreRoutines.add( MutableStateFlow(Routines(0, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)))
        exploreRoutines.add( MutableStateFlow(Routines(1, R.drawable.registration_background,
            "This is a sample routine text", "PULL", false)))
        exploreRoutines.add( MutableStateFlow(Routines(2, R.drawable.registration_background,
            "This is a sample routine text", "PUSH", false)))
        exploreRoutines.add( MutableStateFlow(Routines(3, R.drawable.registration_background,
            "This is a sample routine text", "PULL 3", false)))
        exploreRoutines.add( MutableStateFlow(Routines(4, R.drawable.registration_background,
            "This is a sample routine text", "PUSH 4", false)))
        exploreRoutines.add( MutableStateFlow(Routines(5, R.drawable.registration_background,
            "This is a sample routine text", "PULL 5", false)))
        exploreRoutines.add( MutableStateFlow(Routines(6, R.drawable.registration_background,
            "This is a sample routine text", "PUSH 6", false)))
        exploreRoutines.add( MutableStateFlow(Routines(7, R.drawable.registration_background,
            "This is a sample routine text", "PULL 7", false, points = 10)))

        userRoutines.add( MutableStateFlow(Routines(8, R.drawable.registration_background,
            "This is a sample routine text", "MY PUSH 8", false, points = 3)))
        userRoutines.add( MutableStateFlow(Routines(9, R.drawable.registration_background,
            "This is a sample routine text", "MY PULL 9", false, points = 10)))
        userRoutines.add( MutableStateFlow(Routines(10, R.drawable.registration_background,
            "This is a sample routine text", "MY PUSH 10", false, points = 1)))
      userRoutines.add( MutableStateFlow(Routines(11, R.drawable.registration_background,
            "This is a sample routine text", "MY PULL 11", false)))
    }
}
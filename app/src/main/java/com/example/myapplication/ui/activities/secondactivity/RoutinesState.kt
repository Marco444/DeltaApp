package com.example.myapplication.ui.activities.secondactivity


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.R
import com.example.myapplication.data.Routines
import kotlinx.coroutines.flow.MutableStateFlow

class RoutinesState {


    var userRoutines by mutableStateOf(listOf(
        MutableStateFlow(Routines(8, R.drawable.registration_background,
            "This is a sample routine text", "MY PUSH 8", false, points = 3)),
        MutableStateFlow(Routines(9, R.drawable.registration_background,
            "This is a sample routine text", "MY PULL 9", false, points = 10)),
        MutableStateFlow(Routines(10, R.drawable.registration_background,
            "This is a sample routine text", "MY PUSH 10", false, points = 1)),
        MutableStateFlow(Routines(11, R.drawable.registration_background,
            "This is a sample routine text", "MY PULL 11", false))
    ))

    var exploreRoutines by mutableStateOf(listOf(
            MutableStateFlow(Routines(0, R.drawable.registration_background,
                "This is a sample routine text", "PUSH", false)),
            MutableStateFlow(Routines(1, R.drawable.registration_background,
                "This is a sample routine text", "PULL", false)),
            MutableStateFlow(Routines(2, R.drawable.registration_background,
                "This is a sample routine text", "PUSH", false)),
            MutableStateFlow(Routines(3, R.drawable.registration_background,
                "This is a sample routine text", "PULL 3", false)),
            MutableStateFlow(Routines(4, R.drawable.registration_background,
                "This is a sample routine text", "PUSH 4", false)),
            MutableStateFlow(Routines(5, R.drawable.registration_background,
                "This is a sample routine text", "PULL 5", false)),
            MutableStateFlow(Routines(6, R.drawable.registration_background,
                "This is a sample routine text", "PUSH 6", false)),
            MutableStateFlow(Routines(7, R.drawable.registration_background,
                "This is a sample routine text", "PULL 7", false, points = 10))
        ))
}
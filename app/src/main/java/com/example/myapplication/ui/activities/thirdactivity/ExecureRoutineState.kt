package com.example.myapplication.ui.activities.thirdactivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.ui.classes.CyclesExercise
import com.example.myapplication.ui.classes.Exercise
import com.example.myapplication.ui.classes.RoutineExercises
import com.example.myapplication.ui.classes.Routines
import kotlinx.coroutines.flow.MutableStateFlow

class ExecuteRoutine{


    var exercises by mutableStateOf(List<MutableStateFlow<List<CyclesExercise>>>(3){ MutableStateFlow(
        listOf()
    ) })

    var allExercises by mutableStateOf(listOf<CyclesExercise>())


    var currentRoutine : MutableStateFlow<Routines> = MutableStateFlow (Routines(0,"","","Rutina de ejemplo", added = true, difficulty = ""))
    var routineId : Int = 0
}
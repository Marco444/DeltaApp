package com.example.myapplication.ui.activities.thirdactivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.data.Routines
import com.example.myapplication.ui.classes.CyclesExercise
import com.example.myapplication.ui.classes.Exercise
import com.example.myapplication.ui.classes.RoutineExercises
import kotlinx.coroutines.flow.MutableStateFlow

class ExecuteRoutine{


    var warmUp by mutableStateOf(emptyList<CyclesExercise>())
    var coolDown by mutableStateOf(emptyList<CyclesExercise>())
    var mainSet by mutableStateOf(emptyList<CyclesExercise>())

    var currentRoutine : Routines = Routines(0,0,"","Rutina de ejemplo", added = true)
    var routineId : Int = 0
}
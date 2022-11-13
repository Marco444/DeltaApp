package com.example.myapplication.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.data.Exercise
import com.example.myapplication.data.RoutineExercises
import com.example.myapplication.data.Routines
import kotlinx.coroutines.flow.MutableStateFlow

class ExecuteRoutine{

    var exercises: RoutineExercises = RoutineExercises(
        mutableListOf(Exercise(0,0,15f,15f,1,"Pecho1","aaa")),
        mutableListOf(Exercise(0,1,15f,15f,1,"Descanso","aaa",false)),
        mutableListOf(Exercise(0,2,15f,15f,1,"Pecho3","aaa")))
    var currentRoutine : Routines = Routines(0,0,"","Pecho", added = true)
    var routineId : Int = 0
}
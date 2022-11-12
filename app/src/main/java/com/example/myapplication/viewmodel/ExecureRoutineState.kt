package com.example.myapplication.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.data.Exercise
import com.example.myapplication.data.RoutineExercises
import kotlinx.coroutines.flow.MutableStateFlow

class ExecuteRoutine{
    var actualRoutineId = mutableStateOf(8)
    var exercises: RoutineExercises = RoutineExercises(mutableListOf(Exercise(0,0,15,15,1,"Pecho1","aaa")),mutableListOf(
        Exercise(0,1,15,15,1,"Pecho2","aaa")
    ),mutableListOf(Exercise(0,2,15,15,1,"Pecho3","aaa")))
    var actualOrder = MutableStateFlow(0)
    var actualExercise = MutableStateFlow(Exercise(0,0,0,0,0,"AA","AAA"))
    init {
        actualExercise.value = exercises.warmUpExercises.find { it.order == 0 }!!
    }
}
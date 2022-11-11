package com.example.myapplication.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.data.Exercise
import com.example.myapplication.data.RoutineExercises

class ExecuteRoutine{
    var actualRoutineId = mutableStateOf(8)
    var exercises: RoutineExercises = RoutineExercises(mutableListOf(Exercise(0,0,15,15,1,"Pecho1","aaa")),mutableListOf(
        Exercise(0,1,15,15,1,"Pecho2","aaa")
    ),mutableListOf(Exercise(0,2,15,15,1,"Pecho3","aaa")))
    var actualOrder = mutableStateOf(0)
    var actualExercise = mutableStateOf(Exercise(0,0,0,0,0,"AA","AAA"))
}
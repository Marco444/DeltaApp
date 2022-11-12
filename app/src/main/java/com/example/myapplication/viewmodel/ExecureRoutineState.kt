package com.example.myapplication.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.data.Exercise
import com.example.myapplication.data.RoutineExercises
import kotlinx.coroutines.flow.MutableStateFlow

class ExecuteRoutine{

    var exercises: RoutineExercises = RoutineExercises(
        mutableListOf(Exercise(0,0,15,15,1,"Pecho1","aaa")),
        mutableListOf(Exercise(0,1,15,15,1,"Pecho2","aaa")),
        mutableListOf(Exercise(0,2,15,15,1,"Pecho3","aaa")))

    //var actualExercise = MutableStateFlow(Exercise(0,1,0,0,0,"AA","AAA"))

}
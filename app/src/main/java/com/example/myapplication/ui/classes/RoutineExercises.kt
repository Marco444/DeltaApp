package com.example.myapplication.ui.classes

data class RoutineExercises (
    val warmUpExercises : List<CyclesExercise> = mutableListOf(),
    val mainSetExercises : List<CyclesExercise> = mutableListOf(),
    val coolDownExercises : List<CyclesExercise> = mutableListOf()
)

data class Exercise(
    var duration: Int =0,
    var order: Int = 0,
    var weight : Float = 0f,
    var repetitions : Float = 0f,
    var id: Int = 0,
    var name: String = "",
    var detail : String = "",
    var isExercise : Boolean = true
)
package com.example.myapplication.data



data class RoutineExercises (
    val warmUpExercises : List<Exercise> = mutableListOf(),
    val mainSetExercises : List<Exercise> = mutableListOf(),
    val coolDownExercises : List<Exercise> = mutableListOf()
)

data class Exercise(
    var duration: Int =0,
    var order: Int = 0,
    var weight : Int = 0,
    var repetitions : Int = 0,
    var id: Int = 0,
    var name: String = "",
    var detail : String = ""
)

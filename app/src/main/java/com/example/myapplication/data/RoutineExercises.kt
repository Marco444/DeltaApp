package com.example.myapplication.data



data class RoutineExercises (
    val warmUpExercises : List<Exercise> = mutableListOf(),
    val mainSetExercises : List<Exercise> = mutableListOf(),
    val coolDownExercises : List<Exercise> = mutableListOf()
)

data class Exercise(
    var duration: Int,
    var order: Int,
    var weight : Int,
    var repetitions : Int,
    var id: Int,
    var name: String,
    var detail : String
)

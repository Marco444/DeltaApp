package com.example.myapplication.data



data class RoutineExercises (
    val warmUpExercises : List<Exercise> = mutableListOf(),
    val mainSetExercises : List<Exercise> = mutableListOf(),
    val coolDownExercises : List<Exercise> = mutableListOf()
)

data class Exercise(
    val duration: Int,
    val order: Int,
    val weight : Int,
    val repetitions : Int,
    val id: Int,
    val name: String,
    val detail : String
)

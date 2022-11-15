package com.example.myapplication.data.network.model

import com.example.myapplication.ui.classes.CyclesExercise
import com.example.myapplication.ui.classes.RoutinesCycles
import com.google.gson.annotations.SerializedName

data class NetworkExercisesCycle (
    @SerializedName("exercise")
    val exercise: NetworkExercise,

    @SerializedName("order")
    val order : Int,

    @SerializedName("duration")
    val duration : Int,

    @SerializedName("repetitions")
    val repetitions : Int,

    @SerializedName("metadata")
    val metadata : NetworkEmptyMetadata
    ){
    fun asModel() : CyclesExercise {
        return CyclesExercise(
            id = exercise.id,
            name = exercise.name,
            detail = exercise.detail,
            type = exercise.type,
            order = order,
            repetitions = repetitions.toFloat(),
            duration = duration
        )
    }
}
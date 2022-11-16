package com.example.myapplication.data.network.model

import com.example.myapplication.ui.classes.CycleMetadata
import com.example.myapplication.ui.classes.CyclesExercise
import com.google.gson.annotations.SerializedName

class NetworkExerciseMetadata (
    @SerializedName("id")
    val id : Int,

    @SerializedName("sets")
    val sets : Int,

    @SerializedName("weight")
    val weight : Int,

    @SerializedName("rest")
    val rest : Int
){
    fun asModel() : CycleMetadata {
        return CycleMetadata(
            id = id,
            sets = sets,
            weight = weight,
            rest = rest
        )
    }
}
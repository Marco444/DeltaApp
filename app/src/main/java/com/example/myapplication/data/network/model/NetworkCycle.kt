package com.example.myapplication.data.network.model

import com.example.myapplication.ui.classes.RoutinesCycles
import com.google.gson.annotations.SerializedName

data class NetworkCycle(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("detail")
    var detail: String,

    @SerializedName("type")
    val type : String,

    @SerializedName("order")
    val order : Int,

    @SerializedName("repetitions")
    val repetitions : Int,

    @SerializedName("metadata")
    val metadata : List<NetworkExerciseMetadata>? = listOf()
){
    fun asModel() : RoutinesCycles {
        return RoutinesCycles(
            id = id,
            name = name,
            detail = detail,
            type = type,
            order = order,
           repetitions = repetitions,
            cycleMetadata = metadata?.map { it.asModel() }
        )
    }
}

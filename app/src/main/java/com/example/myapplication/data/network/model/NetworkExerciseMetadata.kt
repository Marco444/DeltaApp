package com.example.myapplication.data.network.model

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

)
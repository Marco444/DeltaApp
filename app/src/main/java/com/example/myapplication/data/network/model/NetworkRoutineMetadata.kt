package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkRoutineMetadata (
    @SerializedName("isFavorite")
    var isFavorite : Boolean = false,

    @SerializedName("img")
    var img : String? = ""
)
package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkLiteRoutine (
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String? = "",

    @SerializedName("detail")
    var detail: String? = "",


    @SerializedName("isPublic")
    var isPublic : Boolean,

    @SerializedName("difficulty")
    var difficulty : String? = null,


    @SerializedName("category")
    var category: NetworkCategory? = NetworkCategory(1),

    @SerializedName("metadata")
    var metadata: NetworkRoutineMetadata? = null
)
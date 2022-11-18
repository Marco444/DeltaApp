package com.example.myapplication.data.network.model

import com.example.myapplication.ui.classes.Routines
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

data class NetworkRoutine (

    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String? = "",

    @SerializedName("detail")
    var detail: String? = "",

    @SerializedName("date")
    var date : Date? = null,

    @SerializedName("score")
    var score : Int? = 0,

    @SerializedName("isPublic")
    var isPublic : Boolean,

    @SerializedName("difficulty")
    var difficulty : String? = null,

    @SerializedName("user")
    var user : NetworkUserLite? = null,

    @SerializedName("category")
    var category: NetworkCategory? = NetworkCategory(1),

    @SerializedName("metadata")
    var metadata: NetworkRoutineMetadata? = null
){
    fun asModel() : Routines {
        return Routines(
            id = id,
            img = metadata?.img ?: "",
            description = detail?: "",
            title = name?: "",
            points = MutableStateFlow(score?: 0),
           favourite = metadata?.isFavorite ?: false,
            difficulty = difficulty?: "",
            isPublic = isPublic,
            delta = metadata?.delta,
            ownerId = user?.id?: 0
        )
    }
}


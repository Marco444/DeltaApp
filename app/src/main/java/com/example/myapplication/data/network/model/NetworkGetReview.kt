package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkGetReview (
    @SerializedName("id")
    val id : Int,
    @SerializedName("date")
    val date : Date,
    @SerializedName("score")
    val score:Int,
    @SerializedName("review")
    val review:String
    )
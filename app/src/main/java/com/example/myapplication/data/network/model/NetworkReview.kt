package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkReview (
    @SerializedName("score")
    val score :Int,
    @SerializedName("review")
    val review:String
)
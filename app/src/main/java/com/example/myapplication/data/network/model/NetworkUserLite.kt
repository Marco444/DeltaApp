package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NetworkUserLite (
    @SerializedName("id")
    var id: Int?,
    @SerializedName("username")
    var username: String,
    @SerializedName("gender")
    var gender: String? = null,
    @SerializedName("avatarUrl")
    var avatarUrl: String? = null,
    @SerializedName("date")
    var date: Date,
    @SerializedName("lastActivity")
    var lastActivity: Date? = null,
)
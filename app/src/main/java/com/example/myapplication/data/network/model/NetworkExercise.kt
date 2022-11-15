package com.example.myapplication.data.network.model

import android.telecom.Call.Details
import com.google.gson.annotations.SerializedName
import java.util.Date

data class NetworkExercise (
        @SerializedName("id")
        val id:Int,

        @SerializedName("name")
        val name:String,

        @SerializedName("detail")
        val detail : String,

        @SerializedName("type")
        val type: String,

        @SerializedName("date")
        val date: Date,

        @SerializedName("order")
        val order: Int

        )
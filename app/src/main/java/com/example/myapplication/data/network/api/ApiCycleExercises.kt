package com.example.myapplication.data.network.api

import com.example.myapplication.data.network.model.NetworkCycle
import com.example.myapplication.data.network.model.NetworkExercisesCycle
import com.example.myapplication.data.network.model.NetworkPagedContent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiCycleExercises {
    @GET("cycles/{cycleId}/exercises")
    suspend fun getCycleExercises(@Path("cycleId") cycleId : Int) : Response<NetworkPagedContent<NetworkExercisesCycle>>

    @GET("cycles/{cycleId}/exercises/{exerciseId}")
    suspend fun getCycleExercise(@Path("cycleId") cycleId : Int,@Path("exerciseId") exerciseId : Int) : Response<NetworkExercisesCycle>

}
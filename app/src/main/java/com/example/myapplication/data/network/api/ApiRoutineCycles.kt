package com.example.myapplication.data.network.api

import com.example.myapplication.data.network.model.NetworkCycle
import com.example.myapplication.data.network.model.NetworkPagedContent
import com.example.myapplication.data.network.model.NetworkRoutine
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRoutineCycles {

    @GET("routines/{routineId}/cycles")
    suspend fun getRoutineCycles(@Path("routineId") routineId: Int) : Response<NetworkPagedContent<NetworkCycle>>

    @GET("routines/{routineId}/cycles/{cycleId}")
    suspend fun getRoutineCycle(@Path("routineId") routineId: Int,@Path("cycleId") cycleId: Int) : Response<NetworkCycle>

}
package com.example.myapplication.data.network.api

import com.example.myapplication.data.network.model.NetworkPagedContent
import com.example.myapplication.data.network.model.NetworkRoutine
import com.example.myapplication.data.network.model.NetworkSport
import retrofit2.Response
import retrofit2.http.*

interface ApiRoutinService {
    @GET("routines")
    suspend fun getRoutines() : Response<NetworkPagedContent<NetworkRoutine>>

    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int) : Response<NetworkRoutine>

    //TODO debemos cambiar el network routine que recibimos que el que mandamos pues uno tiene al usuario
    @PUT("routines/{routineId}")
    suspend fun modifyRoutine(@Path("routineId") sportId: Int, @Body routine: NetworkRoutine) : Response<NetworkRoutine>

    @DELETE("routine/{routineId}")
    suspend fun deleteRoutine(@Path("routineId") routineId: Int) : Response<Unit>
}
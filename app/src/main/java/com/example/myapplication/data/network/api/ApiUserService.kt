package com.example.myapplication.data.network.api

import com.example.myapplication.data.network.model.NetworkCredentials
import ar.edu.itba.example.api.data.network.model.NetworkToken
import com.example.myapplication.data.network.model.NetworkPagedContent
import com.example.myapplication.data.network.model.NetworkRoutine
import com.example.myapplication.data.network.model.NetworkUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiUserService {
    @POST("users/login")
    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>

    @POST("users/logout")
    suspend fun logout(): Response<Unit>

    @GET("users/current")
    suspend fun getCurrentUser(): Response<NetworkUser>

    @GET("users/{userId}/routines")
    suspend fun getUserRoutines(@Path("userId") id : Int): Response<NetworkPagedContent<NetworkRoutine>

>}

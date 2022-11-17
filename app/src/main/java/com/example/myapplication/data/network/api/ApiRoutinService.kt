package com.example.myapplication.data.network.api

import com.example.myapplication.data.network.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiRoutinService {
    @GET("routines")
    suspend fun getRoutines(@Query("page")page:Int,@Query("search")searchString:String?) : Response<NetworkPagedContent<NetworkRoutine>>

    @GET("routines/{routineId}")
    suspend fun getRoutine(@Path("routineId") routineId: Int) : Response<NetworkRoutine>

    @PUT("routines/{routineId}")
    suspend fun modifyRoutine(@Path("routineId") routineId: Int, @Body routine: NetworkRoutine) : Response<NetworkRoutine>

    @DELETE("routines/{routineId}")
    suspend fun deleteRoutine(@Path("routineId") routineId: Int) : Response<Unit>

    @POST("routines")
    suspend fun addRoutine(@Body routine: NetworkRoutine) : Response<Unit>

    @POST("reviews/{routineId}")
    suspend fun addReview(@Path("routineId") routineId: Int,@Body review : NetworkReview):Response<NetworkGetReview>
}
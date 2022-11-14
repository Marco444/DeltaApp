package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.network.RoutinesRemoteDataSource
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.data.network.api.RetrofitClient
import com.example.myapplication.util.SessionManager
import com.example.myapplication.data.network.UserRemoteDataSource
import com.example.myapplication.data.repository.RoutinesRepository

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(RetrofitClient.getApiUserService(this), sessionManager)

    private val routinesRemoteDataSource: RoutinesRemoteDataSource
        get() = RoutinesRemoteDataSource(RetrofitClient.getApiRoutineService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val routinesRepository : RoutinesRepository
        get() = RoutinesRepository(routinesRemoteDataSource)
}

package com.example.myapplication.ui

import android.app.Application
import com.example.myapplication.data.repository.SportRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.data.network.api.RetrofitClient
import com.example.myapplication.util.SessionManager
import com.example.myapplication.data.network.SportRemoteDataSource
import com.example.myapplication.data.network.UserRemoteDataSource

class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(RetrofitClient.getApiUserService(this), sessionManager)

    private val sportRemoteDataSource: SportRemoteDataSource
        get() = SportRemoteDataSource(RetrofitClient.getApiSportService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val sportRepository: SportRepository
        get() = SportRepository(sportRemoteDataSource)
}
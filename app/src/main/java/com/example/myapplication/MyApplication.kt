package com.example.myapplication

import android.app.Application
import androidx.compose.ui.graphics.Color
import com.example.myapplication.data.network.CyclesExercisesRemoteDataSource
import com.example.myapplication.data.network.RoutinesCyclesRemoteDataSource
import com.example.myapplication.data.network.RoutinesRemoteDataSource
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.data.network.api.RetrofitClient
import com.example.myapplication.util.SessionManager
import com.example.myapplication.data.network.UserRemoteDataSource
import com.example.myapplication.data.repository.CyclesExercisesRepository
import com.example.myapplication.data.repository.RoutinesCycleRepository
import com.example.myapplication.data.repository.RoutinesRepository




class MyApplication : Application() {


    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(RetrofitClient.getApiUserService(this), sessionManager)

    private val routinesRemoteDataSource: RoutinesRemoteDataSource
        get() = RoutinesRemoteDataSource(RetrofitClient.getApiRoutineService(this), sessionManager)
    private val routinesCyclesRemoteDataSource: RoutinesCyclesRemoteDataSource
        get() = RoutinesCyclesRemoteDataSource(RetrofitClient.getApiRoutinesCycles(this))
    private val cyclesExerciseRemoteDataSource: CyclesExercisesRemoteDataSource
        get() = CyclesExercisesRemoteDataSource(RetrofitClient.getApiCyclesExercise(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val routinesRepository : RoutinesRepository
        get() = RoutinesRepository(routinesRemoteDataSource)
    val routinesCycleRepository : RoutinesCycleRepository
        get() = RoutinesCycleRepository(routinesCyclesRemoteDataSource)
    val cyclesExerciseRepository : CyclesExercisesRepository
        get() = CyclesExercisesRepository(cyclesExerciseRemoteDataSource)
}

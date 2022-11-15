package com.example.myapplication.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import com.example.myapplication.MyApplication

@Composable
fun getViewModelFactory(defaultArgs: Bundle? = null): ViewModelFactory {
    val application: MyApplication = LocalContext.current.applicationContext as MyApplication
    val sessionManager = application.sessionManager
    val userRepository = application.userRepository
   // val sportRepository = application.sportRepository
    return ViewModelFactory(sessionManager, userRepository, LocalSavedStateRegistryOwner.current, defaultArgs)
}

@Composable
fun getRoutineViewModelFactory(defaultArgs: Bundle? = null) : RoutineViewModelFactory{
    val application: MyApplication = LocalContext.current.applicationContext as MyApplication
    val routinesRepository = application.routinesRepository
    val userRepository = application.userRepository

    return RoutineViewModelFactory( routinesRepository,userRepository, LocalSavedStateRegistryOwner.current, defaultArgs)
}
@Composable
fun getExecRoutineViewModelFactory(defaultArgs: Bundle? = null,routineId : Int) : ExecuteRoutineViewModelFactory{
    val application: MyApplication = LocalContext.current.applicationContext as MyApplication
    val cyclesExercise = application.cyclesExerciseRepository
    val routinesCycles = application.routinesCycleRepository

    return ExecuteRoutineViewModelFactory(cyclesExercise,routinesCycles, routineId, LocalSavedStateRegistryOwner.current, defaultArgs)
}
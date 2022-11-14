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


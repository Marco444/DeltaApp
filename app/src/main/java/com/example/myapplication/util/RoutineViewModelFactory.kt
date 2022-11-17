package com.example.myapplication.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.myapplication.data.repository.RoutinesRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.ui.activities.mainactivity.UserViewModel
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel

class RoutineViewModelFactory constructor(
    private val routinesRepository: RoutinesRepository,
    private val userRepository: UserRepository,

    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(RoutinesViewModel::class.java) ->
                RoutinesViewModel(routinesRepository,userRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
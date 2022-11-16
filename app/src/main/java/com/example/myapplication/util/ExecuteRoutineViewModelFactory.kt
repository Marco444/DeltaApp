package com.example.myapplication.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.myapplication.data.repository.CyclesExercisesRepository
import com.example.myapplication.data.repository.RoutinesCycleRepository
import com.example.myapplication.data.repository.RoutinesRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel
import com.example.myapplication.ui.activities.thirdactivity.ExecuteRoutineViewModel

class ExecuteRoutineViewModelFactory constructor(
    private val cyclesExercisesRepository: CyclesExercisesRepository,
    private val routinesCycleRepository: RoutinesCycleRepository,
    private val routinesRepository : RoutinesRepository,
    private val routineId : Int,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(ExecuteRoutineViewModel::class.java) ->
                ExecuteRoutineViewModel(routineId,cyclesExercisesRepository,routinesCycleRepository, routinesRepository )
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
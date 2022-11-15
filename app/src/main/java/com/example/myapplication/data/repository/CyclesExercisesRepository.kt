package com.example.myapplication.data.repository

import com.example.myapplication.data.network.CyclesExercisesRemoteDataSource
import com.example.myapplication.data.network.RoutinesCyclesRemoteDataSource
import com.example.myapplication.ui.classes.CyclesExercise
import com.example.myapplication.ui.classes.RoutinesCycles

class CyclesExercisesRepository (
    private val remoteDataSource: CyclesExercisesRemoteDataSource
) {


    suspend fun getCycleExercises(cycleId:Int): List<CyclesExercise> {
        return remoteDataSource.getCycleExercises(cycleId).content.map { it.asModel() }
    }

    suspend fun getCycleExercise(cycleId: Int,exerciseId:Int) : CyclesExercise {
        return remoteDataSource.getCycleExercise(cycleId,exerciseId).asModel()
    }



}

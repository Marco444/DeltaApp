package com.example.myapplication.data.repository

import com.example.myapplication.data.Routines
import com.example.myapplication.data.network.RoutinesCyclesRemoteDataSource
import com.example.myapplication.data.network.RoutinesRemoteDataSource
import com.example.myapplication.ui.classes.RoutinesCycles
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutinesCycleRepository(
    private val remoteDataSource: RoutinesCyclesRemoteDataSource
) {


    suspend fun getRoutinCycles(routinId:Int): List<RoutinesCycles> {
        return remoteDataSource.getRoutineCycles(routinId).content.map { it.asModel() }
    }

    suspend fun getRoutinCycle(routinId: Int,cycleId:Int) : RoutinesCycles {
        return remoteDataSource.getRoutineCycle(routinId,cycleId).asModel()
    }



}
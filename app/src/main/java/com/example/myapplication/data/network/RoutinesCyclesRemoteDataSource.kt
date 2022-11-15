package com.example.myapplication.data.network

import com.example.myapplication.data.network.api.ApiRoutinService
import com.example.myapplication.data.network.api.ApiRoutineCycles
import com.example.myapplication.data.network.model.NetworkCycle
import com.example.myapplication.data.network.model.NetworkExercisesCycle
import com.example.myapplication.data.network.model.NetworkPagedContent
import com.example.myapplication.data.network.model.NetworkRoutine

class RoutinesCyclesRemoteDataSource (private val apiRoutineCycles: ApiRoutineCycles): RemoteDataSource(){

    suspend fun getRoutineCycles(routingId : Int) : NetworkPagedContent<NetworkCycle> {
        return handleApiResponse {
            apiRoutineCycles.getRoutineCycles(routingId)
        }
    }
    suspend fun getRoutineCycle(routingId: Int,cycleId : Int): NetworkCycle {
        return handleApiResponse {
            apiRoutineCycles.getRoutineCycle(routingId,cycleId)
        }
    }
}
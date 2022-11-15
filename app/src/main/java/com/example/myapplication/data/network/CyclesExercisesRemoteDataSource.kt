package com.example.myapplication.data.network

import com.example.myapplication.data.network.api.ApiCycleExercises
import com.example.myapplication.data.network.api.ApiRoutineCycles
import com.example.myapplication.data.network.model.NetworkCycle
import com.example.myapplication.data.network.model.NetworkExercisesCycle
import com.example.myapplication.data.network.model.NetworkPagedContent
import com.example.myapplication.ui.classes.CyclesExercise

class CyclesExercisesRemoteDataSource (private val apiRoutineCycles: ApiCycleExercises): RemoteDataSource(){


        suspend fun getCycleExercises(cycleId : Int) : NetworkPagedContent<NetworkExercisesCycle> {
            return handleApiResponse {
                apiRoutineCycles.getCycleExercises(cycleId)
            }
        }
        suspend fun getCycleExercise(cycleId: Int,exerciseId : Int): NetworkExercisesCycle {
            return handleApiResponse {
                apiRoutineCycles.getCycleExercise(cycleId,exerciseId)
            }
        }
}

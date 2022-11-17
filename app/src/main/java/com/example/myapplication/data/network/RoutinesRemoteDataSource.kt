package com.example.myapplication.data.network

import com.example.myapplication.data.network.api.ApiRoutinService
import com.example.myapplication.data.network.model.NetworkGetReview
import com.example.myapplication.data.network.model.NetworkPagedContent
import com.example.myapplication.data.network.model.NetworkReview
import com.example.myapplication.data.network.model.NetworkRoutine

class RoutinesRemoteDataSource(private val apiRoutineService: ApiRoutinService): RemoteDataSource() {

    suspend fun getRoutines(page:Int) : NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines(page)
        }
    }
    suspend fun getRoutine(id:Int):NetworkRoutine{
        return handleApiResponse {
            apiRoutineService.getRoutine(id)
        }
    }
    suspend fun modifyRoutine(routine: NetworkRoutine):NetworkRoutine{
        return handleApiResponse {
            apiRoutineService.modifyRoutine(routine.id,routine)
        }
    }
    suspend fun deleteRoutine(id:Int){
         return handleApiResponse {
             apiRoutineService.deleteRoutine(id)
         }
    }

    suspend fun addRoutine(routine: NetworkRoutine) {
        return handleApiResponse {
            apiRoutineService.addRoutine(routine)
        }
    }
    suspend fun addReview(routineId : Int,review: NetworkReview): NetworkGetReview {
        return handleApiResponse {
            apiRoutineService.addReview(routineId,review)
        }
    }
}
package com.example.myapplication.data.network

import com.example.myapplication.data.network.api.ApiRoutinService
import com.example.myapplication.data.network.model.NetworkPagedContent
import com.example.myapplication.data.network.model.NetworkRoutine

class RoutinesRemoteDataSource(private val apiRoutineService: ApiRoutinService): RemoteDataSource() {

    suspend fun getRoutines() : NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines()
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
        /* return handleApiResponse {
             apiRoutineService.modifyRoutine(id,)
         }*/
    }
}
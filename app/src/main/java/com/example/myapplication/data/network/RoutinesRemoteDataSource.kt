package com.example.myapplication.data.network

import com.example.myapplication.data.network.api.ApiRoutinService
import com.example.myapplication.data.network.model.NetworkGetReview
import com.example.myapplication.data.network.model.NetworkPagedContent
import com.example.myapplication.data.network.model.NetworkReview
import com.example.myapplication.data.network.model.NetworkRoutine
import com.example.myapplication.util.SessionManager

class RoutinesRemoteDataSource(private val apiRoutineService: ApiRoutinService, private val sessionManager: SessionManager): RemoteDataSource() {

    suspend fun getRoutines(page:Int,searchRoutine:String?) : NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiRoutineService.getRoutines(page,searchRoutine)
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

    suspend fun removeFavourite(id: Int) {
        return handleApiResponse {
            apiRoutineService.removeFavourite(id)
        }
    }

    suspend fun addFavourite(id: Int) {
        return handleApiResponse {
            apiRoutineService.addFavourite(id)
        }
    }


    suspend fun getFavourites(page: Int) : NetworkPagedContent<NetworkRoutine>{
        return handleApiResponse {
            apiRoutineService.getFavourite(page)
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

    fun saveDisplayRoutineImage(value: Boolean){
        sessionManager.saveDisplayRoutineImage(value)
    }

    fun saveExecuteRoutineLiteMode(value: Boolean){
        sessionManager.saveExecuteRoutineLiteMode(value)
    }

    fun getDisplayRoutineImage(): Boolean{
        return sessionManager.loadDisplayRoutineImage()
    }

    fun getExecuteRoutineLiteMode(): Boolean{
        return sessionManager.loadExecuteRoutineLiteMode()
    }
}
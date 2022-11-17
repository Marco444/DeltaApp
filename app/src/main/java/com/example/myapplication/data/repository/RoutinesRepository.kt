package com.example.myapplication.data.repository

import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.data.network.RoutinesRemoteDataSource
import com.example.myapplication.ui.activities.secondactivity.PagedRoutines
import com.example.myapplication.ui.activities.thirdactivity.Review
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutinesRepository(
    private val remoteDataSource: RoutinesRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val routinesMutex = Mutex()

    // Cache of the latest sports got from the network.
    private var routines: List<Routines> = emptyList()


    private var page = 0
    private var isLastPage = false

    suspend fun getRoutines(refresh: Boolean = false,page:Int,searchText:String?): PagedRoutines {
        if (refresh || routines.isEmpty()) {
            val result = remoteDataSource.getRoutines(page,searchText)
            // Thread-safe write to latestNews
            routinesMutex.withLock {
                this.routines = result.content.map { it.asModel() }
                this.page = result.page
                this.isLastPage = result.isLastPage
            }
        }

        return PagedRoutines(routinesMutex.withLock { this.routines },page,isLastPage)
    }

    suspend fun getRoutine(routineId: Int) : Routines {
        return remoteDataSource.getRoutine(routineId).asModel()
    }

    suspend fun getFavourites(page:Int): PagedRoutines {
        val result = remoteDataSource.getFavourites(page)
        val content = result.content.map { it.asModel() }
        return PagedRoutines( content, result.page, result.isLastPage)
    }


    suspend fun addFavourite(id: Int) {
        return remoteDataSource.addFavourite(id)
    }

    suspend fun removeFavourite(id: Int) {
        return remoteDataSource.removeFavourite(id)
    }

    suspend fun addRoutine(routine: Routines) {
        remoteDataSource.addRoutine(routine.asNetworkModel())
        routinesMutex.withLock {
            this.routines = emptyList()
        }
    }

    suspend fun modifyRoutine(routine: Routines) : Routines {
        val updateRoutine = remoteDataSource.modifyRoutine(routine.asNetworkModel()).asModel()
        routinesMutex.withLock {
            this.routines = emptyList()
        }
        return updateRoutine
    }

    suspend fun deleteRoutine(routineId: Int) {
        routinesMutex.withLock {
            this.routines = emptyList()
        }
        remoteDataSource.deleteRoutine(routineId)
    }
    suspend fun addReview(routineId:Int,review : Review): Review {
        return remoteDataSource.addReview(routineId,review.asNetworkModel()).asModel()
    }

    fun saveDisplayRoutineImage(value: Boolean){
        remoteDataSource.saveDisplayRoutineImage(value)
    }

    fun saveExecuteRoutineLiteMode(value: Boolean){
        remoteDataSource.saveExecuteRoutineLiteMode(value)
    }

    fun getDisplayRoutineImage(): Boolean{
        return remoteDataSource.getDisplayRoutineImage()
    }

    fun getExecuteRoutineLiteMode(): Boolean{
        return remoteDataSource.getExecuteRoutineLiteMode()
    }

}
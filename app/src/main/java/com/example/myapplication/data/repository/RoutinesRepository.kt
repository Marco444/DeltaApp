package com.example.myapplication.data.repository

import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.data.network.RoutinesRemoteDataSource
import com.example.myapplication.ui.activities.secondactivity.PagedRoutines
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

    suspend fun getRoutines(refresh: Boolean = false,page:Int): PagedRoutines {
        if (refresh || routines.isEmpty()) {
            val result = remoteDataSource.getRoutines(page)
            // Thread-safe write to latestNews
            routinesMutex.withLock {
                this.routines = result.content.map { it.asModel() }
                this.page = result.page
                this.isLastPage = result.isLastPage
            }
        }

        return PagedRoutines(routinesMutex.withLock { this.routines },page,isLastPage)
    }

    suspend fun getRoutine(sportId: Int) : Routines {
        return remoteDataSource.getRoutine(sportId).asModel()
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

}
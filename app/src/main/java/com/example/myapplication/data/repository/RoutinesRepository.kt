package com.example.myapplication.data.repository

import com.example.myapplication.data.Routines
import com.example.myapplication.data.model.Sport
import com.example.myapplication.data.network.RoutinesRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RoutinesRepository(
    private val remoteDataSource: RoutinesRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val routinesMutex = Mutex()
    // Cache of the latest sports got from the network.
    private var routines: List<Routines> = emptyList()

    suspend fun getRoutines(refresh: Boolean = false): List<Routines> {
        if (refresh || routines.isEmpty()) {
            val result = remoteDataSource.getRoutines()
            // Thread-safe write to latestNews
            routinesMutex.withLock {
                this.routines = result.content.map { it.asModel() }
            }
        }

        return routinesMutex.withLock { this.routines }
    }

    suspend fun getRoutine(sportId: Int) : Routines {
        return remoteDataSource.getRoutine(sportId).asModel()
    }



    suspend fun modifyRoutine(routine: Routines) : Routines? {
       /* val updateRoutine = remoteDataSource.modifyRoutine(routine.asNetworkModel()).asModel()
        routinesMutex.withLock {
            this.sports = emptyList()
        }
        return updatedSport*/
        return null
    }

    suspend fun deleteRoutine(routineId: Int) {
        remoteDataSource.deleteRoutine(routineId)
        routinesMutex.withLock {
            this.routines = emptyList()
        }
    }

}
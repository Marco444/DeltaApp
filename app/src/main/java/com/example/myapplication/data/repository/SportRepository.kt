package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Sport
import com.example.myapplication.data.network.SportRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SportRepository(
    private val remoteDataSource: SportRemoteDataSource
) {
    // Mutex to make writes to cached values thread-safe.
    private val sportsMutex = Mutex()
    // Cache of the latest sports got from the network.
    private var sports: List<Sport> = emptyList()

    suspend fun getSports(refresh: Boolean = false): List<Sport> {
        if (refresh || sports.isEmpty()) {
            val result = remoteDataSource.getSports()
            // Thread-safe write to latestNews
            sportsMutex.withLock {
                this.sports = result.content.map { it.asModel() }
            }
        }

        return sportsMutex.withLock { this.sports }
    }

    suspend fun getSport(sportId: Int) : Sport {
        return remoteDataSource.getSport(sportId).asModel()
    }

    suspend fun addSport(sport: Sport) : Sport {
        val newSport = remoteDataSource.addSport(sport.asNetworkModel()).asModel()
        sportsMutex.withLock {
            this.sports = emptyList()
        }
        return newSport
    }

    suspend fun modifySport(sport: Sport) : Sport {
        val updatedSport = remoteDataSource.modifySport(sport.asNetworkModel()).asModel()
        sportsMutex.withLock {
            this.sports = emptyList()
        }
        return updatedSport
    }

    suspend fun deleteSport(sportId: Int) {
        remoteDataSource.deleteSport(sportId)
        sportsMutex.withLock {
            this.sports = emptyList()
        }
    }
}
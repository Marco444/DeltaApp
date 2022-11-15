package com.example.myapplication.data.repository

import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.data.model.User
import com.example.myapplication.data.network.UserRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val currentUserMutex = Mutex()
    // Cache of the current user got from the network.
    private var currentUser: User? = null

    private val userRoutinesMutex = Mutex()

    private var userRoutines: List<Routines> = emptyList()

    suspend fun login(username: String, password: String) {
        remoteDataSource.login(username, password)
    }

    suspend fun logout() {
        remoteDataSource.logout()
    }

    //Esto es para no ir a pedir el usuario cada vez que hacemos currentUser
    suspend fun getCurrentUser(refresh: Boolean) : User? {
        if (refresh || currentUser == null) {
            val result = remoteDataSource.getCurrentUser()
            // Thread-safe write to latestNews
            currentUserMutex.withLock {
                this.currentUser = result.asModel()
            }
        }

        return currentUserMutex.withLock { this.currentUser }
    }
    suspend fun getUserRoutine(refresh: Boolean,id : Int): List<Routines>{
        if (refresh || userRoutines.isEmpty() && currentUser != null) {
            val result = remoteDataSource.getUserRoutines(id)
            // Thread-safe write to latestNews
            userRoutinesMutex.withLock {
                this.userRoutines = result.content.map { it.asModel() }
            }
        }

        return userRoutinesMutex.withLock { this.userRoutines }
    }
}


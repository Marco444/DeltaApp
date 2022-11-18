package com.example.myapplication.data.repository

import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.data.model.User
import com.example.myapplication.data.network.UserRemoteDataSource
import kotlinx.coroutines.runBlocking
import com.example.myapplication.ui.activities.secondactivity.PagedRoutines
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import retrofit2.Response

import com.example.myapplication.data.network.model.NetworkUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource
) {

    // Mutex to make writes to cached values thread-safe.
    private val currentUserMutex = Mutex()
    // Cache of the current user got from the network.
    private var currentUser: User? = null

    private val userRoutinesMutex = Mutex()

    private var userRoutines: List<Routines> = emptyList()

    private var isLastPage = false

    private var page = 0

    suspend fun login(username: String, password: String) {
        try {
            remoteDataSource.login(username, password)
            isAuthMutex.withLock {
                authenticate.update { true }
            }

        }catch (e: Exception){
            throw e
        }

    }

    var isAuthenticated = MutableStateFlow(false)
    private val isAuthMutex = Mutex()
    suspend fun logout() {
        remoteDataSource.logout()
    }
    val authenticate : MutableStateFlow<Boolean> = MutableStateFlow(remoteDataSource.getSessionManager().loadAuthToken() != null)


    fun authenticate(): MutableStateFlow<Boolean> {
        return authenticate
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

    fun checkCurrentUser(): Boolean {
        var logged: Boolean = false
        runBlocking {
            try {
                val response = remoteDataSource.checkCurrentUser()
                val body = response.body()
                if (response.isSuccessful && body != null) { logged = true }
            } catch (e: Exception) {
                logged = false
            }
        }
        return logged
    }

    suspend fun getUserRoutine(refresh: Boolean,id : Int, page: Int): PagedRoutines{
        if (refresh || userRoutines.isEmpty() && currentUser != null) {
            val result = remoteDataSource.getUserRoutines(id, page)
            // Thread-safe write to latestNews
            userRoutinesMutex.withLock {
                this.userRoutines = result.content.map { it.asModel()}
                this.page = result.page
                this.isLastPage = result.isLastPage
            }
        }

        return PagedRoutines(userRoutinesMutex.withLock { this.userRoutines }, page, isLastPage)
    }
}


package com.example.myapplication.data.network

import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.data.network.api.ApiUserService
import com.example.myapplication.data.network.model.NetworkCredentials
import com.example.myapplication.data.network.model.NetworkPagedContent
import com.example.myapplication.data.network.model.NetworkRoutine
import com.example.myapplication.util.SessionManager
import com.example.myapplication.data.network.model.NetworkUser
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response

class UserRemoteDataSource(
    private val apiUserService: ApiUserService,
    private val sessionManager: SessionManager
) : RemoteDataSource() {


    suspend fun login(username: String, password: String) {
        val response = handleApiResponse {
            apiUserService.login(NetworkCredentials(username, password))
        }
        sessionManager.saveAuthToken(response.token)
    }
    fun getSessionManager():SessionManager{
        return sessionManager
    }
    suspend fun logout() {
        handleApiResponse { apiUserService.logout() }
        sessionManager.removeAuthToken()
    }

    suspend fun checkCurrentUser() : Response<NetworkUser> {
       return apiUserService.getCurrentUser()
    }

    suspend fun getCurrentUser() : NetworkUser {
        return handleApiResponse { apiUserService.getCurrentUser() }
    }

    suspend fun getUserRoutines(id:Int, page: Int) : NetworkPagedContent<NetworkRoutine> {
        return handleApiResponse {
            apiUserService.getUserRoutines(id, page)
        }
    }


}
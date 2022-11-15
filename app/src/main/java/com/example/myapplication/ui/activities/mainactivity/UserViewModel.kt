package com.example.myapplication.ui.activities.mainactivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.util.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager,

    ) : ViewModel() {


    var userState = MutableStateFlow(UserState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set
  
    fun login(username: String, password: String) = viewModelScope.launch {
        userState.update { it.copy(
            isFetching = true,
            message = null,
            errorBoolean = false,
        )}
        runCatching {
            userRepository.login(username, password)
        }.onSuccess { response ->
            userState.update {
                it.copy(
                    isFetching = false,
                    isAuthenticated = true
                )
            }
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            userState.update {
                it.copy(
                    message = e.message,
                    errorBoolean = true,
                    isFetching = false
                )
            }
        }
    }

    fun logout() = viewModelScope.launch {
        userState.update {
            it.copy(
                isFetching = true,
                message = null
            )
        }
        runCatching {
            userRepository.logout()
        }.onSuccess { response ->
            userState.update {
                it.copy(
                    isFetching = false,
                    isAuthenticated = false,
                    currentUser = null,
                )
            }
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.

            userState.update {
                it.copy(

                    message = e.message,
                    isFetching = false
                )
            }
        }
    }

    fun getCurrentUser() = viewModelScope.launch {
        userState.update {
            it.copy(
                isFetching = true,
                message = null
            )
        }
        runCatching {
            userRepository.getCurrentUser(userState.value.currentUser == null)
        }.onSuccess { response ->
            userState.update {
                it.copy(
                    isFetching = false,
                    currentUser = response
                )
            }
        }.onFailure { e ->
            // Handle the error and notify the UI when appropriate.
            userState.update {
                it.copy(
                    message = e.message,
                    isFetching = false
                )
            }
        }
    }
}
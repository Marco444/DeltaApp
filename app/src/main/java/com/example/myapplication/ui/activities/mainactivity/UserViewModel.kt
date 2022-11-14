package com.example.myapplication.ui.activities.mainactivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {


    var userState = MutableStateFlow(UserState())
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
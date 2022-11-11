package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel : ViewModel() {

    private val _userState = MutableStateFlow(UserState())

    fun isLoggedIn(): StateFlow<Boolean> {
        return _userState.asStateFlow().value.loggedIn.asStateFlow()
    }

    fun loginAttempt(username: String, password: String) {
        _userState.value.loggedIn.value = true
    }

}
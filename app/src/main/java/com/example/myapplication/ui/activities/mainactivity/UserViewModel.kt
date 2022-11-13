package com.example.myapplication.ui.activities.mainactivity

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Exercise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel : ViewModel() {

    private val _userState = MutableStateFlow(UserState())
    val uiState: StateFlow<UserState>
        get() = _userState.asStateFlow()

    //la funcion deberia hacer la llamada a la api y devolver si
    //fue exitosa o no
    fun loginAttempt(username: String, password: String): Boolean {
        _userState.value.loggedIn.value = true
        return true;
    }

}
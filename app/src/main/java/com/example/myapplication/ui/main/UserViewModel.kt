package com.example.myapplication.ui.main

import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.main.UserState
import kotlinx.coroutines.flow.MutableStateFlow

class UserViewModel : ViewModel() {

    private val _userState = MutableStateFlow(UserState())


    //la funcion deberia hacer la llamada a la api y devolver si
    //fue exitosa o no
    fun loginAttempt(username: String, password: String): Boolean {
        _userState.value.loggedIn.value = true
        return true;
    }

}
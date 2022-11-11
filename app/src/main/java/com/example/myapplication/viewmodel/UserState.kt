package com.example.myapplication.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.Flow

data class User(
    val username: String = "",
    val password: String = ""
)

class UserState {
    val user: User = User()
    var loggedIn: MutableStateFlow<Boolean> = MutableStateFlow(false)
}
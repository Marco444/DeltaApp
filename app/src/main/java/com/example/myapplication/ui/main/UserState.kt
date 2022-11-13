package com.example.myapplication.ui.main

import kotlinx.coroutines.flow.MutableStateFlow

data class User(
    val username: String = "",
    val password: String = ""
)

class UserState {
    val user: User = User()

    //la idea de hacerlo un mutableState flow tambien es para ver si asi
    //se propaga bien, podria ser booleano y ya esta
    var loggedIn: MutableStateFlow<Boolean> = MutableStateFlow(false)
}
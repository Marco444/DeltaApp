package com.example.myapplication.ui.activities.mainactivity

import com.example.myapplication.data.model.User


data class UserState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val message: String? = null,
    val errorBoolean:Boolean? = false
)

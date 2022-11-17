package com.example.myapplication.ui.activities.mainactivity

import com.example.myapplication.data.model.User
import com.example.myapplication.ui.activities.thirdactivity.ExecuteRoutine
import kotlinx.coroutines.flow.MutableStateFlow


data class UserState(
    val isAuthenticated: MutableStateFlow<Boolean>,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val message: String? = null,
    val errorBoolean:Boolean? = false,
)

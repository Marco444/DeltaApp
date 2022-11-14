package com.example.myapplication.data.model

import java.util.Date

data class User(
    var id: Int?,
    var username: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var lastActivity: Date? = null,
    var avatarUrl: String?
)

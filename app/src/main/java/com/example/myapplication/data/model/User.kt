package com.example.myapplication.data.model

import java.util.Date

//Aca creo otro clase que maneja el user de la layer de data,
//mas alla que la de lo que representa en el ui layer
data class User(
    var id: Int,
    var username: String? = "",
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = "",
    var lastActivity: Date? = null,
    var avatarUrl: String? = ""
)

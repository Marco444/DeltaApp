package com.example.myapplication.ui.classes

data class FetchState (
    var isFetching : Boolean = false,
    var error : Boolean = false,
    var message : String = ""
        )
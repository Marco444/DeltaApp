package com.example.myapplication.data.network

class DataSourceException(
    code: Int,
    message: String,
    details: List<String>?
) : Exception(message)
package com.example.myapplication.util

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.R

class SessionManager (context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    companion object {
        const val AUTH_TOKEN = "auth_token"
        const val PREFERENCES_NAME = "preferences"
    }

    fun loadAuthToken(): String? {
        return preferences.getString(AUTH_TOKEN, null)
    }

    fun removeAuthToken() {
        val editor = preferences.edit()
        editor.remove(AUTH_TOKEN)
        editor.apply()
    }

    fun saveAuthToken(token: String) {
        val editor = preferences.edit()
        editor.putString(AUTH_TOKEN, token)
        editor.apply()
    }
}
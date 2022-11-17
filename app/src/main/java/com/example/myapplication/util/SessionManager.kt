package com.example.myapplication.util

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.R

class SessionManager (context: Context) {
    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    companion object {
        const val DISPLAY_ROUTINE_IMAGE = "display_routine_image"
        val EXECUTE_ROUTINE_LITE_MODE = "execute_routine_lite_mode"
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

    fun loadDisplayRoutineImage():Boolean{
        return preferences.getBoolean(DISPLAY_ROUTINE_IMAGE, true)
    }

    fun loadExecuteRoutineLiteMode():Boolean{
        return preferences.getBoolean(EXECUTE_ROUTINE_LITE_MODE, false)
    }

    fun saveDisplayRoutineImage(value: Boolean){
        val editor = preferences.edit()
        editor.putBoolean(DISPLAY_ROUTINE_IMAGE, value)
        editor.apply()
    }

    fun saveExecuteRoutineLiteMode(value: Boolean){
        val editor = preferences.edit()
        editor.putBoolean(EXECUTE_ROUTINE_LITE_MODE, value)
        editor.apply()
    }

}

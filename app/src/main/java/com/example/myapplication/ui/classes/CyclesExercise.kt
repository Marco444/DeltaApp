package com.example.myapplication.ui.classes

data class CyclesExercise (
        val id: Int = 0,
        val name:String = "",
        val detail:String = "",
        val type:String = "",
        val order:Int = 1,
        var repetitions:Float = 0f,
        var sets:Int = 0,
        var weight:Float = 0f,
        var rest:Int = 0,
        val duration:Int,
        var isExercise : Boolean = true,
        var index:Int? = 0
)
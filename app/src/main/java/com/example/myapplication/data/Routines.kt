package com.example.myapplication.data

import android.graphics.Color
import com.example.myapplication.ui.theme.Green

class RoutineProgress (
    private val sessions: Int,
    val agreggatePerformance: Float
) {
    fun progressTile(): String {
        return if(agreggatePerformance < 50) "Let's go!"
        else if (agreggatePerformance < 70) "Well done!"
        else "Excellent job!"
    }

    fun progressDescription(): String {
        return "You have done this routine a total " +
                " of $sessions times, and you are $agreggatePerformance percent " +
                "better than the expected progress in " +
                "average"
    }

//    fun sliderColor(): Color {
//        return Color.RED
//    }
}

data class Routines (
    val id: Int,
    val img: Int,
    val description: String,
    val title: String,
    var added: Boolean,
    var favourite: Boolean = false,
    var points: Int = 0,
    val routineProgress: RoutineProgress = RoutineProgress(0, 40f)
)

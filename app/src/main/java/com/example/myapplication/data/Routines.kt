package com.example.myapplication.data

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.theme.GreenSuccess
import com.example.myapplication.ui.theme.Red
import com.example.myapplication.ui.theme.Yellow
import kotlinx.coroutines.flow.MutableStateFlow

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

    fun color(): Color {
       return if(agreggatePerformance < 50) Red
       else if(agreggatePerformance < 60) Yellow
       else GreenSuccess
    }
}

data class Routines (
    val id: Int,
    val img: Int,
    val description: String,
    val title: String,
    var added: Boolean,
    var favourite: Boolean = false,
    var points: Int = 0,
    var changed: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val routineProgress: RoutineProgress = RoutineProgress(0, 80f),
    val exercises: RoutineExercises = RoutineExercises(mutableListOf(Exercise(0,0,15,15,1,"Pecho","aaa")),mutableListOf(Exercise(0,0,15,15,1,"Pecho","aaa")),mutableListOf(Exercise(0,0,15,15,1,"Pecho","aaa")))
)

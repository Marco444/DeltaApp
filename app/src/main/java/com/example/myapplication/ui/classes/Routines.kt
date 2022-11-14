package com.example.myapplication.data

import androidx.compose.ui.graphics.Color
import com.example.myapplication.R
import com.example.myapplication.ui.classes.Exercise
import com.example.myapplication.ui.classes.RoutineExercises
import com.example.myapplication.ui.theme.GreenSuccess
import com.example.myapplication.ui.theme.Red
import com.example.myapplication.ui.theme.Yellow
import kotlinx.coroutines.flow.MutableStateFlow

class RoutineProgress (
    private val sessions: Int,
    val agreggatePerformance: Float
) {
    fun progressTile(): String {
        return if(sessions == 0) "No information"
        else if(agreggatePerformance < 50) "Let's go!"
        else if (agreggatePerformance < 70) "Well done!"
        else "Great job!"
    }

    fun progressDescription(): String {
        return "You have done this routine a total " +
                " of $sessions times, and you are $agreggatePerformance percent " +
                "better than the expected progress in " +
                "average"
    }

    fun color(): Color {
       return if(sessions == 0) Color.Gray
       else if(agreggatePerformance < 50) Red
       else if(agreggatePerformance < 60) Yellow
       else GreenSuccess
    }
}

data class Routines (
    val id: Int,
    val img: Int = R.drawable.registration_background,
    val description: String,
    val title: String,
    var added: Boolean = false,
    var favourite: Boolean = false,
    var points: Int = 0,
    var changed: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val routineProgress: RoutineProgress = RoutineProgress(0, 80f),
    val exercises: RoutineExercises = RoutineExercises(mutableListOf(Exercise(0,0,15f,15f,1,"Pecho","aaa")),mutableListOf(
        Exercise(0,1,15f,15f,1,"Pecho","aaa")
    ),mutableListOf(Exercise(0,2,15f,15f,1,"Pecho","aaa")))
)

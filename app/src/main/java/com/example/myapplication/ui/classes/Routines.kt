package com.example.myapplication.ui.classes

import androidx.compose.ui.graphics.Color
import com.example.myapplication.R
import com.example.myapplication.data.network.model.NetworkRoutine
import com.example.myapplication.data.network.model.NetworkRoutineMetadata
import com.example.myapplication.ui.classes.CyclesExercise

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
    val id: Int = 0,
    val img: String = "",
    val description: String = "",
    val title: String = "",
    var added: Boolean = false,
    var favourite: Boolean = false,
    var points: MutableStateFlow<Int> = MutableStateFlow(0),
    var changed: MutableStateFlow<Boolean> = MutableStateFlow(false),
    val routineProgress: RoutineProgress = RoutineProgress(0, 80f),
    val difficulty : String = "",
    val isPublic : Boolean = false,
    var delta : Float? = 0f,
    val ownerId : Int = 0
){
    fun asNetworkModel(): NetworkRoutine {
        return NetworkRoutine(
            id = id,
            name = title,
            detail = description,
            difficulty = difficulty,
            isPublic = isPublic,
            score = points.value,
            metadata = NetworkRoutineMetadata(favourite,img,delta)
        )
    }
}

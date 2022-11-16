package com.example.myapplication.ui.activities.secondactivity


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.myapplication.R
import com.example.myapplication.ui.classes.Routines
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File
import java.util.*

class RoutinesState {


    var userRoutines by mutableStateOf(emptyList<MutableStateFlow<Routines>>())

    var exploreRoutines by mutableStateOf((emptyList<MutableStateFlow<Routines>>()))
//            MutableStateFlow(
//                Routines(0, R.drawable.registration_background,
//                "This is a sample routine text", "PUSH", false)
//            ),
//            MutableStateFlow(
//                Routines(1, R.drawable.registration_background,
//                "This is a sample routine text", "PULL", false)
//            ),
//            MutableStateFlow(
//                Routines(2, R.drawable.registration_background,
//                "This is a sample routine text", "PUSH", false)
//            ),
//            MutableStateFlow(
//                Routines(3, R.drawable.registration_background,
//                "This is a sample routine text", "PULL 3", false)
//            ),
//            MutableStateFlow(
//                Routines(4, R.drawable.registration_background,
//                "This is a sample routine text", "PUSH 4", false)
//            ),
//            MutableStateFlow(
//                Routines(5, R.drawable.registration_background,
//                "This is a sample routine text", "PULL 5", false)
//            ),
//            MutableStateFlow(
//                Routines(6, R.drawable.registration_background,
//                "This is a sample routine text", "PUSH 6", false)
//            ),
//            MutableStateFlow(
//                Routines(7, R.drawable.registration_background,
//                "This is a sample routine text", "PULL 7", false, points = 10)
//            )

}
data class PagedRoutines(
    val content : List<Routines>,
    val page : Int,
    val isLastPage : Boolean
)
private fun encoder(filePath: String): String{
    val bytes = File(filePath).readBytes()
    val base64 = Base64.getEncoder().encodeToString(bytes)
    return base64
}
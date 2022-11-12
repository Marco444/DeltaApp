package com.example.myapplication.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Exercise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.roundToInt

class ExecuteRoutineViewModel : ViewModel() {

    private val _execRoutineState = MutableStateFlow(ExecuteRoutine())
    var actualExercise = MutableStateFlow(Exercise(name = "pecho 0"))
        private set

   var next = 0

    fun nextExercise(){
        println(actualExercise.value)
//        for(cycle in _execRoutineState.value.exercises)
        var aux = _execRoutineState.value.exercises.warmUpExercises.find { it.order == next }
        if(aux != null) {
            actualExercise.update { aux!! }
            next++
            return
        }
         aux = _execRoutineState.value.exercises.mainSetExercises.find { it.order == next }
        if(aux != null) {
            actualExercise.update { aux!! }
            next++
            return
        }
         aux = _execRoutineState.value.exercises.coolDownExercises.find { it.order == next }
        if(aux != null)
            actualExercise.update { aux }
        next++
    }

    fun hasNext(): Boolean{
        var aux = _execRoutineState.value.exercises.warmUpExercises.find { it.order == next }
        if(aux != null)
            return true
        aux =   _execRoutineState.value.exercises.mainSetExercises.find { it.order ==  next }
        if(aux != null)
            return true
        aux = _execRoutineState.value.exercises.coolDownExercises.find { it.order ==  next }
        if (aux != null)
            return true
        return false
    }

    fun setReps(reps : Float){
        actualExercise.value.repetitions = reps.roundToInt()
    }
    fun setWeight(weight : Float){
        actualExercise.value.weight = weight.roundToInt()
    }
}
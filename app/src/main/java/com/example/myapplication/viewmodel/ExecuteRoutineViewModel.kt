package com.example.myapplication.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Exercise
import kotlinx.coroutines.flow.MutableStateFlow

class ExecuteRoutineViewModel : ViewModel(){
    private val _execRoutineState = MutableStateFlow(ExecuteRoutine())

    private fun setNewExercise(oldExercise: MutableState<Exercise>, newExercise: Exercise){
        oldExercise.value.id = newExercise.id
        oldExercise.value.order = newExercise.order
        oldExercise.value.name = newExercise.name
        oldExercise.value.weight = newExercise.weight
        oldExercise.value.repetitions = newExercise.repetitions
        oldExercise.value.duration = newExercise.duration
        oldExercise.value.detail = newExercise.detail
    }
    fun nextExercise(){
        var aux = _execRoutineState.value.exercises.warmUpExercises.find { it.order ==  _execRoutineState.value.actualOrder.value }
        if(aux != null) {
            setNewExercise(_execRoutineState.value.actualExercise, aux)
            _execRoutineState.value.actualOrder.value += 1
            return
        }
         aux = _execRoutineState.value.exercises.mainSetExercises.find { it.order ==  _execRoutineState.value.actualOrder.value }
        if(aux != null) {
            setNewExercise(_execRoutineState.value.actualExercise, aux)
            _execRoutineState.value.actualOrder.value += 1
            return
        }
         aux = _execRoutineState.value.exercises.coolDownExercises.find { it.order ==  _execRoutineState.value.actualOrder.value }
        if(aux != null)
            setNewExercise(_execRoutineState.value.actualExercise,aux)
        _execRoutineState.value.actualOrder.value += 1

    }
    fun hasNext(): Boolean{
        var aux = _execRoutineState.value.exercises.warmUpExercises.find { it.order ==  _execRoutineState.value.actualOrder.value }
        if(aux != null)
            return true
        aux =   _execRoutineState.value.exercises.mainSetExercises.find { it.order ==  _execRoutineState.value.actualOrder.value }
        if(aux != null)
            return true
        aux = _execRoutineState.value.exercises.coolDownExercises.find { it.order ==  _execRoutineState.value.actualOrder.value }
        if (aux != null)
            return true
        return false
    }
    fun getCurrentExercise() : MutableState<Exercise>{
        return _execRoutineState.value.actualExercise
    }

}
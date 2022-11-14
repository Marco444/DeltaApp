package com.example.myapplication.ui.activities.thirdactivity

import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.classes.Exercise
import com.example.myapplication.ui.classes.Routines
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.roundToInt

class ExecuteRoutineViewModel() : ViewModel() {

    private val _execRoutineState = MutableStateFlow(ExecuteRoutine())

    var exerciseCount = 0

    var actualExercise = MutableStateFlow(Exercise(name = "pecho 0"))
        private set

    private var opinions: List<String> = listOf("Aweful", "Bad", "Regular", "Good", "Excelent")
    private var actualOpinion: Int = 2

    var opinion = MutableStateFlow("Regular")

    init {
        if(hasNext())
            nextExercise()
        exerciseCount = _execRoutineState.value.exercises.warmUpExercises.count() +
                        _execRoutineState.value.exercises.mainSetExercises.count() +
                        _execRoutineState.value.exercises.coolDownExercises.count()
    }
    val state: StateFlow<Exercise>
        get() = actualExercise.asStateFlow()

   var next = 0

    fun routine(id:Int) : Routines {
       return _execRoutineState.value.currentRoutine
    }
    fun nextExercise(){
        setExercise(next, next - 1)
        next++
    }
    fun previusExercise(){
        if(next != 1) {
            setExercise(next - 2, next - 1)
            next--
        }
    }
    private fun setExercise(newOrder: Int,previousOrder : Int){
        var aux = _execRoutineState.value.exercises.warmUpExercises.find { it.order == newOrder }
        if(aux != null) {
            val changeValue = _execRoutineState.value.exercises.warmUpExercises.find { it.order == previousOrder }
            changeValue?.weight = actualExercise.value.weight
            actualExercise.update { aux!! }
            return
        }
         aux = _execRoutineState.value.exercises.mainSetExercises.find { it.order == newOrder }
        if(aux != null) {
            val changeValue = _execRoutineState.value.exercises.warmUpExercises.find { it.order == previousOrder }
            changeValue?.weight = actualExercise.value.weight
            actualExercise.update { aux!! }
            return
        }
         aux = _execRoutineState.value.exercises.coolDownExercises.find { it.order == newOrder }
        if(aux != null) {
            val changeValue = _execRoutineState.value.exercises.warmUpExercises.find { it.order == previousOrder }
            changeValue?.weight = actualExercise.value.weight
            actualExercise.update { aux }
        }
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
        actualExercise.update {oldExercise ->
            oldExercise.repetitions  = reps.roundToInt().toFloat()
            oldExercise
        }
        println(actualExercise.value.repetitions)
    }
    fun setWeight(weight : Float){
        actualExercise.update {oldExercise ->
            oldExercise.weight  = weight
            oldExercise
        }
    }
    fun getRoutineWarmUpExercises() : List<Exercise>{
        return   _execRoutineState.value.exercises.warmUpExercises
    }
    fun getRoutineCoolDownExercises() : List<Exercise>{
        return   _execRoutineState.value.exercises.coolDownExercises
    }
    fun getRoutineMainSetExercises() : List<Exercise>{
        return   _execRoutineState.value.exercises.mainSetExercises
    }

    fun getExercises() : List<Exercise>{
        return getRoutineWarmUpExercises() + getRoutineMainSetExercises() + getRoutineCoolDownExercises()
    }

    fun upOpinion() {
        if(actualOpinion < 4) {
            actualOpinion += 1
            opinion.update { opinions[actualOpinion] }
        }
    }

    fun downOpinion() {
        if(actualOpinion > 0) {
            actualOpinion -= 1
            opinion.update { opinions[actualOpinion] }
        }
    }

    fun cardsExpandable(): Boolean {
        //return screenWidth != WindowWidthSizeClass.Expanded
        return true
    }

}
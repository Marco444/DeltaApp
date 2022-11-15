package com.example.myapplication.ui.activities.thirdactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.classes.Exercise
import com.example.myapplication.data.repository.CyclesExercisesRepository
import com.example.myapplication.data.repository.RoutinesCycleRepository
import com.example.myapplication.ui.classes.CyclesExercise
import com.example.myapplication.ui.classes.Routines
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class ExecuteRoutineViewModel(
      routineId : Int,
      cyclesExercisesRepository: CyclesExercisesRepository,
     routinesCycleRepository: RoutinesCycleRepository,
) : ViewModel() {

    private val _execRoutineState = MutableStateFlow(ExecuteRoutine())
    val executeRoutine: StateFlow<ExecuteRoutine>
        get() = _execRoutineState.asStateFlow()
    var exerciseCount = 0

    var actualExercise = MutableStateFlow(CyclesExercise(0,"","","",0,0f,0,0f,0,0))
        private set

    private var opinions: List<String> = listOf("Aweful", "Bad", "Regular", "Good", "Excelent")
    private var actualOpinion: Int = 2

    var opinion = MutableStateFlow("Regular")

    val state: StateFlow<CyclesExercise>
        get() = actualExercise.asStateFlow()

   var nextWarmUp = 1
    var nextMainSet = 1
    var nextCoolDown = 1
    init {
        viewModelScope.launch {
            val cycles = routinesCycleRepository.getRoutinCycles(routineId)
            _execRoutineState.value.warmUp = cyclesExercisesRepository.getCycleExercises(cycles[0].id)
            _execRoutineState.value.mainSet = cyclesExercisesRepository.getCycleExercises(cycles[1].id)
            _execRoutineState.value.coolDown = cyclesExercisesRepository.getCycleExercises(cycles[2].id)
            nextExercise()
        }
    }
    fun routine(id:Int) : Routines {
       return _execRoutineState.value.currentRoutine
    }
    fun nextExercise(){
        setExercise(nextWarmUp, nextWarmUp - 1)
        //next++
    }
    fun previusExercise(){
        //if(next != 1) {
            //setExercise(next - 2, next - 1)
           // next--
       // }
    }
    private fun setExercise(newOrder: Int,previousOrder : Int){
        var aux = _execRoutineState.value.warmUp.find { it.order == nextWarmUp }
        if(aux != null) {
            nextWarmUp++;
            val changeValue = _execRoutineState.value.warmUp.find { it.order == previousOrder }
            changeValue?.weight = actualExercise.value.weight
            actualExercise.update { aux!! }
            return
        }
         aux = _execRoutineState.value.mainSet.find { it.order == nextMainSet }
        if(aux != null) {
            nextMainSet++;
            val changeValue = _execRoutineState.value.mainSet.find { it.order == previousOrder }
            changeValue?.weight = actualExercise.value.weight
            actualExercise.update { aux!! }
            return
        }
         aux = _execRoutineState.value.coolDown.find { it.order == nextCoolDown }
        if(aux != null) {
            nextCoolDown++;
            val changeValue = _execRoutineState.value.coolDown.find { it.order == previousOrder }
            changeValue?.weight = actualExercise.value.weight
            actualExercise.update { aux }
        }
    }

    fun hasNext(): Boolean{
        var aux = _execRoutineState.value.warmUp.find { it.order == nextWarmUp }
        if(aux != null)
            return true
        aux =   _execRoutineState.value.mainSet.find { it.order ==  nextMainSet }
        if(aux != null)
            return true
        aux = _execRoutineState.value.coolDown.find { it.order ==  nextCoolDown }
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
    fun getRoutineWarmUpExercises() : List<CyclesExercise>{
        return   _execRoutineState.value.warmUp
    }
    fun getRoutineCoolDownExercises() : List<CyclesExercise>{
        return   _execRoutineState.value.coolDown
    }
    fun getRoutineMainSetExercises() : List<CyclesExercise>{
        return  _execRoutineState.value.mainSet
    }

    fun getExercises() : List<CyclesExercise>{
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
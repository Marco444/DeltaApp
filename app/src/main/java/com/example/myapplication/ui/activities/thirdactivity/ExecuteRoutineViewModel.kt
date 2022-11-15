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
    var exerciseCount = 1

    var actualExercise = MutableStateFlow(CyclesExercise(0,"","","",0,0f,0,0f,0,0))
        private set

    private var opinions: List<String> = listOf("Aweful", "Bad", "Regular", "Good", "Excelent")
    private var actualOpinion: Int = 2

    var opinion = MutableStateFlow("Regular")

    val state: StateFlow<CyclesExercise>
        get() = actualExercise.asStateFlow()


    var actualCycle = 0
    var index = -1
    lateinit var iterator:ListIterator<CyclesExercise>
    var isInNext = true
    init {
        viewModelScope.launch {
            val cycles = routinesCycleRepository.getRoutinCycles(routineId)
            _execRoutineState.value.exercises[0].value = cyclesExercisesRepository.getCycleExercises(cycles[0].id)
            _execRoutineState.value.exercises[1].value = cyclesExercisesRepository.getCycleExercises(cycles[1].id)
            _execRoutineState.value.exercises[2].value = cyclesExercisesRepository.getCycleExercises(cycles[2].id)
            _execRoutineState.value.allExercises += _execRoutineState.value.exercises[0].value
            _execRoutineState.value.allExercises += _execRoutineState.value.exercises[1].value
            _execRoutineState.value.allExercises += _execRoutineState.value.exercises[2].value

            iterator = _execRoutineState.value.allExercises.listIterator()
            if(iterator.hasNext())
                actualExercise.update { iterator.next() }
            else
                actualExercise.update { CyclesExercise(0,"","","",0,0f,0,0f,0,0) }
            index = 0

        }
    }
    fun routine(id:Int) : Routines {
       return _execRoutineState.value.currentRoutine
    }
    fun nextExercise(){
        if(!isInNext) {
            iterator.next()
            isInNext = true
        }

        actualExercise.update { iterator.next() }

    }
    fun previusExercise(){
        if(isInNext) {
            iterator.previous()
            isInNext = false
        }

        actualExercise.update { iterator.previous() }

    }
    private fun setExercise(){
        actualExercise.update { _execRoutineState.value.exercises[actualCycle].value[index] }

    }

    fun hasNext(): Boolean{
        return iterator.hasNext()
    }
    fun hasPrevious(): Boolean{
        return iterator.hasPrevious()
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
    private fun getRoutineWarmUpExercises() : List<CyclesExercise>{
        return   _execRoutineState.value.exercises[0].value
    }
    private fun getRoutineCoolDownExercises() : List<CyclesExercise>{
        return   _execRoutineState.value.exercises[1].value
    }
    private fun getRoutineMainSetExercises() : List<CyclesExercise>{
        return  _execRoutineState.value.exercises[2].value
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
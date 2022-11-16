package com.example.myapplication.ui.activities.thirdactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.classes.Exercise
import com.example.myapplication.data.repository.CyclesExercisesRepository
import com.example.myapplication.data.repository.RoutinesCycleRepository
import com.example.myapplication.data.repository.RoutinesRepository
import com.example.myapplication.ui.classes.CyclesExercise
import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.ui.classes.RoutinesCycles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class ExecuteRoutineViewModel(
    private val routineId : Int,
      private val cyclesExercisesRepository: CyclesExercisesRepository,
      private val routinesCycleRepository: RoutinesCycleRepository,
      private val routinesRepository: RoutinesRepository
) : ViewModel() {

    private val _execRoutineState = MutableStateFlow(ExecuteRoutine())
    val executeRoutine: StateFlow<ExecuteRoutine>
        get() = _execRoutineState.asStateFlow()

    var exerciseCount = 1

    var actualExercise = MutableStateFlow(CyclesExercise(0,"","","",0,0f,0,0f,0,0))
        private set

    val state: StateFlow<CyclesExercise>
        get() = actualExercise.asStateFlow()

    lateinit var iterator:ListIterator<CyclesExercise>
    var isInNext = true
   lateinit var  cycles : List<RoutinesCycles>


    init {
        viewModelScope.launch {
            executeRoutine.value.currentRoutine.update { routinesRepository.getRoutine(routineId) }
        }
        viewModelScope.launch {
             cycles = routinesCycleRepository.getRoutinCycles(routineId)
            _execRoutineState.value.exercises[0].value = cyclesExercisesRepository.getCycleExercises(cycles[0].id).map {
                it.rest = cycles[0].id
                it
            }
            _execRoutineState.value.exercises[1].value = cyclesExercisesRepository.getCycleExercises(cycles[1].id)
            _execRoutineState.value.exercises[2].value = cyclesExercisesRepository.getCycleExercises(cycles[2].id)

            initAllExercises()
            exerciseCount = _execRoutineState.value.allExercises.size
            iterator = _execRoutineState.value.allExercises.listIterator()

        }
    }
    private fun initAllExercises(){
        for (cycle in 0..2){
            for (exercise in _execRoutineState.value.exercises[cycle].value){
                for (set in 0..exercise.sets) {
                    val metadata = cycles[cycle].cycleMetadata?.filter { it.id == exercise.id }
                    exercise.rest = metadata?.get(0)?.rest?:0
                    exercise.sets =metadata?.get(0)?.sets?:0
                    exercise.weight = metadata?.get(0)?.weight?.toFloat()!!
                    exercise.index =  _execRoutineState.value.allExercises.size
                    println(exercise)
                    _execRoutineState.value.allExercises += exercise
                    if(exercise.rest != 0)
                        _execRoutineState.value.allExercises += CyclesExercise(duration = exercise.rest, isExercise = false, index = exercise.index!! +1)
                }
            }
        }

    }

    fun finishRoutine(){
        var contador = 0
        var delta = 0f
        for (exercise in _execRoutineState.value.allExercises){
            if (exercise.isExercise)
            {
                contador++
                delta += exercise.weight * exercise.repetitions
            }
        }
        if(contador != 0)
           delta /= contador
        executeRoutine.value.currentRoutine.value.delta = (executeRoutine.value.currentRoutine.value.delta?.plus(
            delta
        ))?.div(2)
        viewModelScope.launch {
            executeRoutine.value.currentRoutine.update { routinesRepository.modifyRoutine(executeRoutine.value.currentRoutine.value)  }  }


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

}
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

    val state: StateFlow<CyclesExercise>
        get() = actualExercise.asStateFlow()



    lateinit var iterator:ListIterator<CyclesExercise>
    var isInNext = true

    init {
        viewModelScope.launch {
            val cycles = routinesCycleRepository.getRoutinCycles(routineId)
            _execRoutineState.value.exercises[0].value = cyclesExercisesRepository.getCycleExercises(cycles[0].id)
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
                    _execRoutineState.value.allExercises += exercise
                    if(exercise.rest != 0)
                        _execRoutineState.value.allExercises += CyclesExercise(duration = exercise.rest, isExercise = false)
                }
            }
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
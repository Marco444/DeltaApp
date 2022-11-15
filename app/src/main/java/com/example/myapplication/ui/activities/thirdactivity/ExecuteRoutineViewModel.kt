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


    var actualCycle = 0
    var index = -1
    init {
        viewModelScope.launch {
            val cycles = routinesCycleRepository.getRoutinCycles(routineId)
            _execRoutineState.value.exercises[0].value = cyclesExercisesRepository.getCycleExercises(cycles[0].id)
            _execRoutineState.value.exercises[1].value = cyclesExercisesRepository.getCycleExercises(cycles[1].id)
            _execRoutineState.value.exercises[2].value = cyclesExercisesRepository.getCycleExercises(cycles[2].id)


            //TODO FALTA CHEQUEAR LOS CICLOS QUE NO TIENE EJS


            actualExercise.update { _execRoutineState.value.exercises[0].value[0] }
            index = 0

        }
    }
    fun routine(id:Int) : Routines {
       return _execRoutineState.value.currentRoutine
    }
    fun nextExercise(){


        val changeValue = _execRoutineState.value.exercises[actualCycle].value[index]
        changeValue.weight = actualExercise.value.weight
        index++
        if (_execRoutineState.value.exercises[actualCycle].value.size == index )
        {
            index = 0
            actualCycle++
        }
        setExercise()

    }
    fun previusExercise(){

        val changeValue = _execRoutineState.value.exercises[actualCycle].value[index]
        changeValue.weight = actualExercise.value.weight
        if (index == 0 && actualCycle != 0)
        {
            actualCycle--;
            index = _execRoutineState.value.exercises[actualCycle].value.size - 1
        }else if( actualCycle != 0) {
            index -= 1
        }
        setExercise()

    }
    private fun setExercise(){
        actualExercise.update { _execRoutineState.value.exercises[actualCycle].value[index] }

    }

    fun hasNext(): Boolean{
        return actualCycle < 2
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
package com.example.myapplication.ui.activities.thirdactivity

import com.example.myapplication.data.Exercise
import com.example.myapplication.data.RoutineExercises
import com.example.myapplication.data.Routines

class ExecuteRoutine{

    var exercises: RoutineExercises = RoutineExercises(
        mutableListOf(Exercise(0,0,15f,15f,1,"Warmup1","Trote moderado para entrar en calor"),
            Exercise(0,1,15f,15f,1,"Warmup2","Sentate y hace fierros qcyo hermano"),
            Exercise(0,2,15f,15f,1,"Warmup3","Algo mas por aca"),
            Exercise(0,3,15f,15f,1,"Warmup4","Guarda con los hombros")
        ),
        mutableListOf(Exercise(0,4,15f,15f,1,"Main1","Trote moderado para entrar en calor"),
            Exercise(0,5,15f,15f,1,"Main2","Sentate y hace fierros qcyo hermano"),
            Exercise(0,6,15f,15f,1,"Main3","Algo mas por aca"),
            Exercise(0,7,15f,15f,1,"Main4","Guarda con los hombros")
        ),
        mutableListOf(Exercise(0,8,15f,15f,1,"CoolDown1","Trote moderado para entrar en calor"),
            Exercise(0,9,15f,15f,1,"CoolDown2","Sentate y hace fierros qcyo hermano"),
            Exercise(0,10,15f,15f,1,"CoolDown3","Algo mas por aca"),
            Exercise(0,11,15f,15f,1,"CoolDown4","Guarda con los hombros")))
    var currentRoutine : Routines = Routines(0,0,"","Rutina de ejemplo", added = true)
    var routineId : Int = 0
}
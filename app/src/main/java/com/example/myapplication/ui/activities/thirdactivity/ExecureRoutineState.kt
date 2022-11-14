package com.example.myapplication.ui.activities.thirdactivity

import com.example.myapplication.data.Exercise
import com.example.myapplication.data.RoutineExercises
import com.example.myapplication.data.Routines

class ExecuteRoutine{

    var exercises: RoutineExercises = RoutineExercises(
        mutableListOf(Exercise(0,0,15f,15f,1,"Cinta","Trote moderado para entrar en calor"),
            Exercise(0,0,15f,15f,1,"Bench Press","Sentate y hace fierros qcyo hermano"),
            Exercise(0,0,15f,15f,1,"Banco inclinado","Algo mas por aca"),
            Exercise(0,0,15f,15f,1,"Vuelos lateras","Guarda con los hombros")
        ),
        mutableListOf(Exercise(0,0,15f,15f,1,"Cinta","Trote moderado para entrar en calor"),
            Exercise(0,0,15f,15f,1,"Bench Press","Sentate y hace fierros qcyo hermano"),
            Exercise(0,0,15f,15f,1,"Banco inclinado","Algo mas por aca"),
            Exercise(0,0,15f,15f,1,"Vuelos lateras","Guarda con los hombros")
        ),
        mutableListOf(Exercise(0,0,15f,15f,1,"Cinta","Trote moderado para entrar en calor"),
            Exercise(0,0,15f,15f,1,"Bench Press","Sentate y hace fierros qcyo hermano"),
            Exercise(0,0,15f,15f,1,"Banco inclinado","Algo mas por aca"),
            Exercise(0,0,15f,15f,1,"Vuelos lateras","Guarda con los hombros")))
    var currentRoutine : Routines = Routines(0,0,"","Pecho", added = true)
    var routineId : Int = 0
}
package com.example.myapplication.ui.classes

import com.example.myapplication.data.network.model.NetworkRoutineMetadata

data class RoutinesCycles (
    val id: Int,
    val name:String,
    val detail:String,
    val type:String,
    val order:Int,
    val repetitions:Int,
    val cycleMetadata : List<CycleMetadata>? = listOf()
)

data class CycleMetadata(
    val id : Int,
    val sets: Int,
    val rest : Int,
    val weight : Int
)
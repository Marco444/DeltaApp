package com.example.myapplication.ui.activities.secondactivity

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.data.repository.RoutinesRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.ui.activities.thirdactivity.ExecuteRoutine
import com.example.myapplication.ui.components.RoutineCard
import com.example.myapplication.ui.components.SortOption
import com.example.myapplication.ui.navigation.NavBarScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RoutinesViewModel(
    private val routinesRepository: RoutinesRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _sortState = MutableStateFlow(SortOption.POINTS)
    private val _routinesState = MutableStateFlow(RoutinesState())
    private var page = 0
    private val _hasNextPage = MutableStateFlow(false)
    val hasNextPage: StateFlow<Boolean>
        get() = _hasNextPage.asStateFlow()
    init {
       getUserRoutines()
        getExploreRoutines()
    }

    private fun getUserRoutines() = viewModelScope.launch {
        viewModelScope.launch {
            val aux = userRepository.getCurrentUser(false)
            aux?.id?.let {
                _routinesState.value.userRoutines = userRepository.getUserRoutine(true, it).map {routine -> MutableStateFlow(routine) } }
        }
    }

    private fun getExploreRoutines() = viewModelScope.launch {
        val response =  routinesRepository.getRoutines(true,page)
        _routinesState.value.exploreRoutines += response.content.map { MutableStateFlow(it) }
        page = response.page
        _hasNextPage.update { !response.isLastPage }

    }
     fun nextPage(){
        if(hasNextPage.value) {
            page++
            getExploreRoutines()
        }
    }

    fun getSortState(): MutableStateFlow<SortOption> {
        return _sortState
    }

    fun setSortState(option: SortOption, screen: NavBarScreen) {
        _sortState.value = option
        sortRoutines(option, screen)

    }

    fun sortRoutines(option: SortOption, screen: NavBarScreen) {
        if(screen == NavBarScreen.Explore)
            _routinesState.value.exploreRoutines = _routinesState.value.exploreRoutines.sortedWith { a: MutableStateFlow<Routines>, b: MutableStateFlow<Routines> -> option.comparator(a, b) }
        else
            _routinesState.value.userRoutines = _routinesState.value.userRoutines.sortedWith { a: MutableStateFlow<Routines>, b: MutableStateFlow<Routines> -> option.comparator(a, b) }
    }

    fun getRoutines(routineCard: RoutineCard): List<MutableStateFlow<Routines>> {
        return if(routineCard == RoutineCard.ExploreRoutine) _routinesState.value.exploreRoutines
        else _routinesState.value.userRoutines
    }

    fun routineUser(id: Int): Routines {
        return _routinesState.value.userRoutines.find { routine ->routine.value.id == id }!!.value
    }

    fun routineExplore(id: Int): Routines {
        return _routinesState.value.exploreRoutines.find { routine ->routine.value.id == id }!!.value
    }

    fun clickedIcon(id: Int) {
            val routine = _routinesState.value.userRoutines.find { routine ->routine.value.id == id }!!
            routine.update { it.copy(favourite = !it.favourite) }
            updateRoutine(routine.value)
    }


     fun deleteRoutine(routineId: Int) = viewModelScope.launch {
        _routinesState.value.userRoutines = emptyList()
        routinesRepository.deleteRoutine(routineId)
        getUserRoutines()
    }

    private fun addRoutine(routine: Routines) =  viewModelScope.launch {
        routinesRepository.addRoutine(routine)
        getUserRoutines()
    }


    private fun updateRoutine(routine: Routines) =  viewModelScope.launch {
        routinesRepository.modifyRoutine(routine)
        getUserRoutines()
    }

    fun isSelected(id: Int, routineCard: RoutineCard): Boolean {
        return if(RoutineCard.ExploreRoutine == routineCard)
           routineExplore(id).added
        else
            _routinesState.value.userRoutines.isNotEmpty() && routineUser(id).favourite
    }

    private var screenWidth: WindowWidthSizeClass = WindowWidthSizeClass.Compact

    fun cardsExpandable(): Boolean {
        return screenWidth != WindowWidthSizeClass.Expanded
    }

    fun setWidth(width: WindowWidthSizeClass) {
        screenWidth = width;
    }


}
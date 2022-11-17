package com.example.myapplication.ui.activities.secondactivity

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.User
import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.data.repository.RoutinesRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.ui.components.RoutineCard
import com.example.myapplication.ui.components.SortOption
import com.example.myapplication.ui.navigation.NavBarScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RoutinesViewModel(
    private val routinesRepository: RoutinesRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _sortState = MutableStateFlow(SortOption.POINTS)
    private val _routinesState = MutableStateFlow(RoutinesState())
    private val _hasNextPageExplore = MutableStateFlow(false)
    private val _hasNextPageUser = MutableStateFlow(false)


    val error = MutableStateFlow(false)
    fun errorHandled() {
        error.update { false }
    }

    private var pageExplore = 0
    private var pageUser = 0
    val hasNextPageUser: StateFlow<Boolean>
        get() = _hasNextPageUser.asStateFlow()
    val hasNextPageExplore: StateFlow<Boolean>
        get() = _hasNextPageExplore.asStateFlow()

    init {
        if(loggedIn())
            getUserRoutines()

        getExploreRoutines()

    }

    private fun loggedIn(): Boolean {
      return userRepository.authenticate().value
    }


    fun showFavorites() = viewModelScope.launch {
        _routinesState.value.exploreRoutines = emptyList()

        var content = routinesRepository.getFavourites(0)
        var favourites = content.content
        while(!content.isLastPage) {
            content = routinesRepository.getFavourites(content.page + 1)
            favourites += content.content
        }

        _routinesState.value.exploreRoutines = favourites.map{ MutableStateFlow(it.copy(favourite = true))}
    }

    private fun addFavourite(id: Int) = viewModelScope.launch{
        runCatching {
            routinesRepository.addFavourite(id)
        }
    }

    private fun removeFavourite(id: Int) = viewModelScope.launch{
        runCatching {
            routinesRepository.removeFavourite(id)
        }
    }

    private fun getUserRoutines() = viewModelScope.launch {
       var aux: User? = User(-1)

        runCatching {
            aux = userRepository.getCurrentUser(false)!!
        }.onSuccess {
            var response: PagedRoutines? = null
            runCatching {
                response = userRepository.getUserRoutine(true, aux!!.id, pageUser)
            }.onSuccess {
                _routinesState.value.userRoutines += response!!.content.map { routine ->
                    MutableStateFlow(
                        routine
                    )
                }
                pageUser = response!!.page
                _hasNextPageUser.update { !response!!.isLastPage }
            }.onFailure {
                error.update { true }
                throw it

            }
        }.onFailure {
            error.update { true }
            throw it

        }
    }
    fun getExploreWithParamsWrapper(text: String?){
        if(text != null) {
            _routinesState.value.exploreRoutines = emptyList()
            getExploreWithParams(text)
        }else{
            _routinesState.value.exploreRoutines = emptyList()
            getExploreRoutines()
        }
    }
    private fun getExploreWithParams(text:String?)= viewModelScope.launch {
        runCatching {
            var page = 0
            var response = routinesRepository.getRoutines(true, page, text)
            page++
            pageExplore = 0
            _hasNextPageExplore.update { false }
            _routinesState.value.exploreRoutines = response.content.map { MutableStateFlow(it) }
            var hasNext = !response.isLastPage
            while (hasNext) {
                response = routinesRepository.getRoutines(true, page++, text)
                _routinesState.value.exploreRoutines += response.content.map { MutableStateFlow(it) }
                pageExplore = response.page
                hasNext = !response.isLastPage
            }
        }.onSuccess{

        }.onFailure {
            error.update { true }
        }
    }

    private fun getExploreRoutines() = viewModelScope.launch {
        var response = PagedRoutines(emptyList(), 0, true)

        runCatching {
            response = routinesRepository.getRoutines(true,pageExplore,null)
        }.onSuccess {
            _routinesState.value.exploreRoutines += response.content.map { MutableStateFlow(it) }
            pageExplore = response.page
            _hasNextPageExplore.update { !response.isLastPage }
        }.onFailure {
            error.update { true }
        }

    }


     fun nextPageUser(){
        if(hasNextPageUser.value) {
            pageUser++
            getUserRoutines()
        }
    }

    fun nextPageExplore(){
        if(hasNextPageExplore.value) {
            pageExplore++
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

    fun clickedIcon(id: Int, routineCard: RoutineCard) {
        if(routineCard != RoutineCard.ExploreRoutine) {
            val routine = _routinesState.value.userRoutines.find { routine -> routine.value.id == id }!!
            routine.update { it.copy(favourite = !it.favourite) }
            updateRoutine(routine.value)
        } else {
            val routine = _routinesState.value.exploreRoutines.find { routine -> routine.value.id == id }!!
            routine.update { it.copy(favourite = !it.favourite) }
            if(routine.value.favourite) {
                addFavourite(routine.value.id)
            } else {
                removeFavourite(routine.value.id)
            }
        }
    }


     fun deleteRoutine(routineId: Int) = viewModelScope.launch {
        _routinesState.value.userRoutines = emptyList()

         runCatching {
             routinesRepository.deleteRoutine(routineId)
         }.onSuccess {
             getUserRoutines()
         }.onFailure {
             error.update { true }
         }

    }

    private fun updateRoutine(routine: Routines) =  viewModelScope.launch {
        runCatching {
            routinesRepository.modifyRoutine(routine)
        }.onSuccess {
            getUserRoutines()
        }.onFailure {
            error.update { true }
        }
    }

    fun isSelected(id: Int, routineCard: RoutineCard): Boolean {
        return if(RoutineCard.ExploreRoutine == routineCard)
           routineExplore(id).favourite
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

    // SETTINGS -> TODO moverlo para arriba una vez que funque
    val displayRoutineImages = MutableStateFlow(routinesRepository.getDisplayRoutineImage())
    val executionRoutineModeLite = MutableStateFlow(routinesRepository.getExecuteRoutineLiteMode())

    fun setDisplayRoutineImages(){
        displayRoutineImages.update { !displayRoutineImages.value }
        routinesRepository.saveDisplayRoutineImage(displayRoutineImages.value)
    }

    fun setExecutionRoutineModeLite(){
        executionRoutineModeLite.update { !executionRoutineModeLite.value }
        routinesRepository.saveExecuteRoutineLiteMode(executionRoutineModeLite.value)
    }

}
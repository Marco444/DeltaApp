package com.example.myapplication.ui.activities.secondactivity

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.User
import com.example.myapplication.data.network.DataSourceException
import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.data.repository.RoutinesRepository
import com.example.myapplication.data.repository.UserRepository
import com.example.myapplication.ui.classes.FetchState
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
    private val _hasNextPageExplore = MutableStateFlow(false)
    private val _hasNextPageUser = MutableStateFlow(false)


    val error = MutableStateFlow(false)
    fun errorHandled() {
        error.update { false }
    }
    private val _fetchingState = MutableStateFlow(FetchState())

    val fetchingState: StateFlow<FetchState>
        get() = _fetchingState.asStateFlow()

    private var pageExplore = 0
    private var pageUser = 0
    val hasNextPageUser: StateFlow<Boolean>
        get() = _hasNextPageUser.asStateFlow()
    val hasNextPageExplore: StateFlow<Boolean>
        get() = _hasNextPageExplore.asStateFlow()

    init {
        getExploreRoutines()

        if(loggedIn())
            getUserRoutines()
    }

    private fun loggedIn(): Boolean {
      return userRepository.authenticate().value
    }


    fun getUserFavoritesWrapper() {
        _routinesState.value.exploreRoutines = emptyList()
        displayUserFavorites(true)
    }

    private fun displayUserFavorites(onlyFavourites: Boolean) = viewModelScope.launch {

        var content = routinesRepository.getFavourites(0)
        val favourites = content.content.toMutableList()
        while (!content.isLastPage) {
            content = routinesRepository.getFavourites(content.page + 1)
            favourites += content.content
        }

        if(onlyFavourites) {
            _routinesState.value.exploreRoutines = favourites.map { MutableStateFlow(it.copy(favourite = true)) }
        } else {
            for (routine in _routinesState.value.exploreRoutines) {
                for (favourite in favourites) {
                    if (routine.value.id == favourite.id)
                        routine.update {it.copy(favourite = true)}
                }
            }
        }

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

    fun getUserRoutines() = viewModelScope.launch {
        if(loggedIn()) {
            var aux: User? = User(-1)
            _fetchingState.update { it.copy(isFetching = true, error = false) }
            runCatching {
                _routinesState.value.userRoutines = emptyList()
                aux = userRepository.getCurrentUser(false)!!
            }.onSuccess {
                var response: PagedRoutines? = null
                runCatching {
                    var page = 0
                    var hasNext = true
                    response = userRepository.getUserRoutine(true, aux!!.id, page++)
                    _routinesState.value.userRoutines = response!!.content.map { MutableStateFlow(it) }
                    hasNext = !response!!.isLastPage
                    while (hasNext){
                        response = userRepository.getUserRoutine(true, aux!!.id, page++)
                        _routinesState.value.userRoutines += response!!.content.map { MutableStateFlow(it) }
                        hasNext = !response!!.isLastPage
                    }

                }.onSuccess {
                    _fetchingState.update { it.copy(isFetching = false, error = false) }

                }.onFailure { apiError ->
                    if(apiError is DataSourceException) {
                        apiError as DataSourceException
                        _fetchingState.update {
                            it.copy(
                                isFetching = false,
                                error = true,
                                message = apiError.message ?: "",
                            )
                        }
                    }

                    error.update { true }
                }
            }.onFailure { apiError ->
                _fetchingState.update { it.copy(isFetching = false, error = true, message = apiError.message?:"") }
                error.update { true }
            }
        }
    }
    fun getExploreWithParamsWrapper(text: String?){
        if(text != null) {
            _routinesState.value.exploreRoutines = emptyList()
            getExploreWithParams(text)
        } else{
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
            displayUserFavorites(onlyFavourites = false)
        }.onSuccess{

        }.onFailure {
            error.update { true }
        }
    }

    fun getExploreRoutines() = viewModelScope.launch {
        _routinesState.value.exploreRoutines = emptyList()
        _fetchingState.update { it.copy(isFetching = true, error = false) }
        runCatching {
            var page = 0
            var response = routinesRepository.getRoutines(true,page++,null)
            _routinesState.value.exploreRoutines = emptyList()
            _routinesState.value.exploreRoutines = response.content.map { MutableStateFlow(it) }
            while (!response.isLastPage) {
                response = routinesRepository.getRoutines(true,page++,null)
                _routinesState.value.exploreRoutines += response.content.map { MutableStateFlow(it) }
            }
            var content = routinesRepository.getFavourites(0)
            val favourites = content.content.toMutableList()
            while (!content.isLastPage) {
                content = routinesRepository.getFavourites(content.page + 1)
                favourites += content.content
            }
            for (routine in _routinesState.value.exploreRoutines) {
                for (favourite in favourites) {
                    if (routine.value.id == favourite.id)
                        routine.update {it.copy(favourite = true)}
                }
            }
            displayUserFavorites(onlyFavourites = false)


            pageExplore = response.page
            _hasNextPageExplore.update { true }
        }.onFailure {

        }.onSuccess {
            _fetchingState.update { it.copy(isFetching = false, error = false) }
            _hasNextPageExplore.update { false }
        }.onFailure { apiError ->
            _fetchingState.update { it.copy(isFetching = false, error = true, message = apiError.message?:"") }
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

    private fun getRoutineUser(id: Int): MutableStateFlow<Routines> {
        val routine = _routinesState.value.userRoutines.find { routine ->routine.value.id == id }
        return if(routine == null) {
            error.update { true }
            MutableStateFlow(Routines())
        } else {
            routine
        }
    }

    private fun getRoutineExplore(id: Int): MutableStateFlow<Routines> {
        val routine = _routinesState.value.exploreRoutines.find { routine ->routine.value.id == id }
        return if(routine == null) {
            error.update { true }
            MutableStateFlow(Routines())
        } else {
            routine
        }
    }

    private fun sortRoutines(option: SortOption, screen: NavBarScreen) {
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
        return getRoutineUser(id).value
    }

    private fun routineExplore(id: Int): Routines {
        return getRoutineExplore(id).value
    }

    private fun userRoutineFavourites(id: Int) {
        val routine = getRoutineUser(id)
        routine.update { it.copy(favourite = !it.favourite) }
        updateRoutine(routine.value)
    }

    private fun exploreRoutineFavourites(id: Int) {
        val routine = getRoutineExplore(id)
        routine.update { it.copy(favourite = !it.favourite) }

        if(routine.value.favourite)
            addFavourite(routine.value.id)
         else
            removeFavourite(routine.value.id)

    }

    fun clickedIcon(id: Int, routineCard: RoutineCard) {
        if(routineCard != RoutineCard.ExploreRoutine)
          userRoutineFavourites(id)
        else exploreRoutineFavourites(id)
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
            //getUserRoutines()
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

    private var screenWidth = MutableStateFlow(WindowWidthSizeClass.Compact)
    val hamburguer = MutableStateFlow(true)

    fun cardsExpandable(): Boolean {
        return screenWidth.value != WindowWidthSizeClass.Expanded
    }

    fun setWidth(width: WindowWidthSizeClass) {
        screenWidth.update { width };
        hamburguer.update { width != WindowWidthSizeClass.Medium }
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
package com.project.viewreview.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.project.viewreview.domain.usecases.movie_list.GetPopularMovies
import com.project.viewreview.domain.usecases.movie_list.GetTopRatedMovies
import com.project.viewreview.domain.usecases.movie_list.GetTrendingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTrendingMoviesUseCase: GetTrendingMovies,
    private val getPopularMoviesUseCase: GetPopularMovies,
    private val getTopRatedMoviesUseCase: GetTopRatedMovies,
) : ViewModel() {

    var state = mutableStateOf(HomeState())
        private set


    val trendingMovies = getTrendingMoviesUseCase().cachedIn(viewModelScope)
    val popularMovies = getPopularMoviesUseCase().cachedIn(viewModelScope)
    val topRatedMovies = getTopRatedMoviesUseCase().cachedIn(viewModelScope)

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.UpdateScrollValue -> updateScrollValue(event.newValue)
            is HomeEvent.UpdateMaxScrollingValue -> updateMaxScrollingValue(event.newValue)
        }
    }

    private fun updateScrollValue(newValue: Int) {
        state.value = state.value.copy(scrollValue = newValue)
    }
    private fun updateMaxScrollingValue(newValue: Int) {
        state.value = state.value.copy(maxScrollingValue = newValue)
    }
}
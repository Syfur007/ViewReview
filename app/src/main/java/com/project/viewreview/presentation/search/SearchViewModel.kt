package com.project.viewreview.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.project.viewreview.domain.usecases.movie_list.SearchMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovieUseCases: SearchMovies
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchMovie -> {
                searchMovie()
            }
        }
    }

    private fun searchMovie() {
        val movies = searchMovieUseCases(
            searchQuery = _state.value.searchQuery
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(movies = movies)
    }


}
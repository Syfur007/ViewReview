package com.project.viewreview.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.viewreview.domain.usecases.movie_list.GetSavedMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getSaveMoviesUseCase: GetSavedMovies
): ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init {
        getMovies()
    }


    private fun getMovies() {
        getSaveMoviesUseCase().onEach { movies ->
            _state.value = _state.value.copy(movies = movies)
        }.launchIn(viewModelScope)
    }

}
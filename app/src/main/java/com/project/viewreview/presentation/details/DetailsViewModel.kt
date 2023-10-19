package com.project.viewreview.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.viewreview.domain.model.Movie
import com.project.viewreview.domain.usecases.movie.DeleteMovie
import com.project.viewreview.domain.usecases.movie.GetSavedMovie
import com.project.viewreview.domain.usecases.movie.UpsertMovie
import com.project.viewreview.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getSavedMovieUseCase: GetSavedMovie,
    private val deleteMovieUseCase: DeleteMovie,
    private val upsertMovieUseCase: UpsertMovie
): ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteMovie -> {
                viewModelScope.launch {
                    val movie = getSavedMovieUseCase(id = event.movie.id)
                    if (movie == null){
                        upsertMovie(movie = event.movie)
                    }else{
                        deleteMovie(movie = event.movie)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private suspend fun deleteMovie(movie: Movie) {
        deleteMovieUseCase(movie = movie)
        sideEffect = UIComponent.Toast("Bookmark Removed")
    }

    private suspend fun upsertMovie(movie: Movie) {
        upsertMovieUseCase(movie = movie)
        sideEffect = UIComponent.Toast("Movie Bookmarked")
    }
}
package com.project.viewreview.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.project.viewreview.domain.usecases.movie.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieUseCases: MovieUseCases
) : ViewModel() {

    val trendingMovies = movieUseCases.getTrendingMovies().cachedIn(viewModelScope)
    val popularMovies = movieUseCases.getPopularMovies().cachedIn(viewModelScope)
    val topRatedMovies = movieUseCases.getTopRatedMovies().cachedIn(viewModelScope)
}
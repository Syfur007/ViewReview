package com.project.viewreview.domain.usecases.movie

data class MovieUseCases(
    val getPopularMovies: GetPopularMovies,
    val getTrendingMovies: GetTrendingMovies,
    val getTopRatedMovies: GetTopRatedMovies,
    val searchMovies: SearchMovies,
    val getMovie: GetMovie
)

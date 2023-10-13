package com.project.viewreview.domain.usecases.movie

import com.project.viewreview.domain.repository.MovieRepository

class GetTrendingMovies(
    private val movieRepository: MovieRepository
) {
    operator fun invoke() = movieRepository.getTrendingMovies()
}
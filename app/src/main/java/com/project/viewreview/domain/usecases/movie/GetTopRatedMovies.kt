package com.project.viewreview.domain.usecases.movie

import com.project.viewreview.domain.repository.MovieRepository

class GetTopRatedMovies(
    private val movieRepository: MovieRepository
) {
    operator fun invoke() = movieRepository.getTopRatedMovies()
}
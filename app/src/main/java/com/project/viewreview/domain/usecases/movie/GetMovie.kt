package com.project.viewreview.domain.usecases.movie

import com.project.viewreview.domain.model.Movie
import com.project.viewreview.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovie @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(movieId: Int): Movie {
        return movieRepository.getMovie(movieId = movieId)
    }
}


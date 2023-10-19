package com.project.viewreview.domain.usecases.movie

import com.project.viewreview.domain.model.Movie
import com.project.viewreview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovie(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(movieId: Int): Flow<Movie> {
        return movieRepository.getMovie(movieId = movieId)
    }
}
package com.project.viewreview.domain.usecases.movie

import com.project.viewreview.data.remote.dto.MovieCredits
import com.project.viewreview.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieCredits @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(movieId: Int): MovieCredits {
        return movieRepository.getMovieCredits(movieId = movieId)
    }
}
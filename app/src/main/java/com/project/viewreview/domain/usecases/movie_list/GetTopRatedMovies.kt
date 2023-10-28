package com.project.viewreview.domain.usecases.movie_list

import com.project.viewreview.domain.repository.MovieRepository
import javax.inject.Inject

class GetTopRatedMovies @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke() = movieRepository.getTopRatedMovies()
}
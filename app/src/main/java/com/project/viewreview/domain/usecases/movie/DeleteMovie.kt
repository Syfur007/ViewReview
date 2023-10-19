package com.project.viewreview.domain.usecases.movie

import com.project.viewreview.data.local.MovieDao
import com.project.viewreview.domain.model.Movie
import javax.inject.Inject

class DeleteMovie @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend operator fun invoke(movie: Movie) {
        movieDao.delete(movie)
    }
}
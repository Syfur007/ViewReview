package com.project.viewreview.domain.usecases.movie

import com.project.viewreview.data.local.MovieDao
import com.project.viewreview.data.remote.dto.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedMovies @Inject constructor(
    private val movieDao: MovieDao
) {

    operator fun invoke(): Flow<List<Movie>> {
        return movieDao.getMovies()
    }

}
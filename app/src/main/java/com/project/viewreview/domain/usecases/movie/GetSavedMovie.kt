package com.project.viewreview.domain.usecases.movie

import com.project.viewreview.data.local.MovieDao
import com.project.viewreview.data.remote.dto.Movie
import javax.inject.Inject

class GetSavedMovie @Inject constructor(
    private val movieDao: MovieDao
) {

    suspend operator fun invoke(id: Int): Movie? {
        return movieDao.getMovie(id = id)
    }

}
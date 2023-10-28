package com.project.viewreview.domain.usecases.movie

import androidx.paging.PagingData
import com.project.viewreview.domain.model.MovieBasic
import com.project.viewreview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingMovies @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<MovieBasic>> {
        return movieRepository.getTrendingMovies()
    }
}
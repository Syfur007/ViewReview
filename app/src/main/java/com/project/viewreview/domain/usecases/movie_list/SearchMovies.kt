package com.project.viewreview.domain.usecases.movie_list

import androidx.paging.PagingData
import com.project.viewreview.domain.model.MovieResponse
import com.project.viewreview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovies @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<MovieResponse>> {
        return movieRepository.searchMovies(searchQuery = searchQuery)
    }
}
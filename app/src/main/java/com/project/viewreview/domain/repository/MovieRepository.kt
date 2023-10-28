package com.project.viewreview.domain.repository

import androidx.paging.PagingData
import com.project.viewreview.data.remote.dto.Movie
import com.project.viewreview.domain.model.MovieBasic
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getTopRatedMovies(): Flow<PagingData<MovieBasic>>

    fun getTrendingMovies(): Flow<PagingData<MovieBasic>>

    fun getPopularMovies(): Flow<PagingData<MovieBasic>>

    fun searchMovies(searchQuery: String): Flow<PagingData<MovieBasic>>

    suspend fun upsertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    fun getMovies(): Flow<List<Movie>>

    suspend fun getMovie(movieId: Int): Movie
}
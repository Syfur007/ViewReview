package com.project.viewreview.domain.repository

import androidx.paging.PagingData
import com.project.viewreview.domain.model.Movie
import com.project.viewreview.domain.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getTopRatedMovies(): Flow<PagingData<MovieResponse>>

    fun getTrendingMovies(): Flow<PagingData<MovieResponse>>

    fun getPopularMovies(): Flow<PagingData<MovieResponse>>

    fun searchMovies(searchQuery: String): Flow<PagingData<MovieResponse>>

    suspend fun upsertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    fun getMovies(): Flow<List<Movie>>

    suspend fun getMovie(movieId: Int): Movie
}
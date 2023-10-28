package com.project.viewreview.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.viewreview.data.local.MovieDao
import com.project.viewreview.data.remote.MovieApi
import com.project.viewreview.data.remote.MoviePagingSource
import com.project.viewreview.data.remote.SearchPagingSource
import com.project.viewreview.data.remote.dto.Movie
import com.project.viewreview.domain.model.MovieResponse
import com.project.viewreview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getTopRatedMovies(): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                MoviePagingSource(movieApi, "top_rated")
            }
        ).flow
    }

    override fun getTrendingMovies(): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                MoviePagingSource(movieApi, "trending")
            }
        ).flow
    }

    override fun getPopularMovies(): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                MoviePagingSource(movieApi, "popular")
            }
        ).flow
    }

    override fun searchMovies(searchQuery: String): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingSource(movieApi, searchQuery)
            }
        ).flow
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return movieApi.getMovie(movieId)
    }

    override suspend fun upsertMovie(movie: Movie) {
        movieDao.upsert(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.delete(movie)
    }

    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getMovies()
    }

}
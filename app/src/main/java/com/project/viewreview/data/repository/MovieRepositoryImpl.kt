package com.project.viewreview.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.viewreview.data.remote.MovieApi
import com.project.viewreview.data.remote.MoviePagingSource
import com.project.viewreview.data.remote.SearchPagingSource
import com.project.viewreview.domain.model.Movie
import com.project.viewreview.domain.model.MovieResponse
import com.project.viewreview.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieApi: MovieApi
): MovieRepository {
    override fun getTopRatedMovies(): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { MoviePagingSource(movieApi, "top_rated") }
        ).flow
    }

    override fun getTrendingMovies(): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { MoviePagingSource(movieApi, "trending") }
        ).flow
    }

    override fun getPopularMovies(): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { MoviePagingSource(movieApi, "popular") }
        ).flow
    }

    override fun searchMovies(searchQuery: String): Flow<PagingData<MovieResponse>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { SearchPagingSource(movieApi, searchQuery) }
        ).flow
    }

    override fun getMovie(movieId: Int): Flow<Movie> {
        return flow {
            emit(movieApi.getMovie(movieId = movieId))
        }
    }

}
package com.project.viewreview.domain.repository

import androidx.paging.PagingData
import com.project.viewreview.domain.model.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getTopRatedMovies(): Flow<PagingData<MovieResponse>>
    fun getTrendingMovies(): Flow<PagingData<MovieResponse>>
    fun getPopularMovies(): Flow<PagingData<MovieResponse>>
}
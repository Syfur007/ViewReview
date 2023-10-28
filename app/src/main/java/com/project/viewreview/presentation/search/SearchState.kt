package com.project.viewreview.presentation.search

import androidx.paging.PagingData
import com.project.viewreview.domain.model.MovieResponse
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val movies: Flow<PagingData<MovieResponse>>? = null
)
package com.project.viewreview.data.remote.dto

import com.project.viewreview.domain.model.MovieBasic

data class MovieListResponse(
    val page: Int,
    val results: List<MovieBasic>,
    val total_pages: Int,
    val total_results: Int
)
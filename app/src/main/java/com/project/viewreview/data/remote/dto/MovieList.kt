package com.project.viewreview.data.remote.dto

import com.project.viewreview.domain.model.MovieResponse

data class MovieList(
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int
)
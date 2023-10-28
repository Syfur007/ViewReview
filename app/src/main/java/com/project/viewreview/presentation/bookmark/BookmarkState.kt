package com.project.viewreview.presentation.bookmark

import com.project.viewreview.data.remote.dto.Movie

data class BookmarkState (
    val movies: List<Movie> = emptyList(),
)
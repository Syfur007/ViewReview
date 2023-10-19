package com.project.viewreview.presentation.bookmark

import com.project.viewreview.domain.model.Movie

data class BookmarkState (
    val movies: List<Movie> = emptyList(),
)
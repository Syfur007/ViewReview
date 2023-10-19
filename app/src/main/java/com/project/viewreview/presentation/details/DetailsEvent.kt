package com.project.viewreview.presentation.details

import com.project.viewreview.domain.model.Movie

sealed class DetailsEvent {
    data class UpsertDeleteMovie(val movie: Movie) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}
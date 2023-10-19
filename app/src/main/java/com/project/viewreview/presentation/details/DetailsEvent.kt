package com.project.viewreview.presentation.details

sealed class DetailsEvent {

    object ToggleBookmark: DetailsEvent()
    object ToggleFavourite: DetailsEvent()
}
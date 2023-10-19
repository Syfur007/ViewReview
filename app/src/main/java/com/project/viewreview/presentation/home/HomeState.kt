package com.project.viewreview.presentation.home

data class HomeState(
    val movieTicker: String = "",
    val isLoading: Boolean = false,
    val scrollValue: Int = 0,
    val maxScrollingValue: Int = 0
)

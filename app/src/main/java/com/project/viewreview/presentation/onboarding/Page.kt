package com.project.viewreview.presentation.onboarding

import androidx.annotation.DrawableRes

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)


val pages = listOf(
    Page(
        title = "Welcome to ViewReview",
        description = "See. Share. Discover.",
        image = com.project.viewreview.R.drawable.onboarding_1
    ),
    Page(
        title = "Find your favorite movies",
        description = "ViewReview allows you to find your favorite movies in one place.",
        image = com.project.viewreview.R.drawable.onboarding_2
    ),
    Page(
        title = "Share your opinion",
        description = "Share your opinion with the community.",
        image = com.project.viewreview.R.drawable.onboarding_3
    ),
)
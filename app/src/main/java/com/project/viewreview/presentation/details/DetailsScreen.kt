package com.project.viewreview.presentation.details

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.project.viewreview.data.remote.dto.FightClub
import com.project.viewreview.data.remote.dto.FightClubCredits
import com.project.viewreview.data.remote.dto.Movie
import com.project.viewreview.data.remote.dto.MovieCredits
import com.project.viewreview.data.remote.dto.Review
import com.project.viewreview.presentation.details.components.DetailsTopBar
import com.project.viewreview.presentation.details.components.MovieDetails
import com.project.viewreview.ui.theme.ViewReviewTheme
import com.project.viewreview.util.Constants.TMDB_BASE_URL
import com.project.viewreview.util.UIComponent

@Composable
fun DetailsScreen(
    movie: Movie,
    movieCredits: MovieCredits,
    movieReviews: List<Review>,
    onEvent: (DetailsEvent) -> Unit,
    sideEffect: UIComponent?,
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = sideEffect) {
        sideEffect?.let {
            when (sideEffect) {
                is UIComponent.Toast -> {
                    Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                    onEvent(DetailsEvent.RemoveSideEffect)
                }

                else -> Unit
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        val scrollState = rememberLazyListState()
        MovieDetails(
            movie = movie,
            movieCredits = movieCredits,
            movieReviews = movieReviews,
            onReviewPost = { reviewText: String ->
                onEvent(DetailsEvent.PostReview(movie.id, reviewText))
            },
            scrollState = scrollState
        )

        DetailsTopBar(
            onBookmarkClick = { onEvent(DetailsEvent.UpsertDeleteMovie(movie)) },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.type = "text/plain"
                    it.putExtra(Intent.EXTRA_TEXT, TMDB_BASE_URL + movie.id)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBackClick = navigateUp
        )
    }
}

@Preview
@Composable
fun Test() {
    ViewReviewTheme {
        DetailsScreen(
            movie = FightClub,
            movieCredits = FightClubCredits,
            movieReviews = emptyList(),
            onEvent = {},
            navigateUp = {},
            sideEffect = null
        )
    }
}
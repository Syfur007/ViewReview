package com.project.viewreview.presentation.details

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.project.viewreview.domain.model.FightClub
import com.project.viewreview.domain.model.Movie
import com.project.viewreview.presentation.details.components.DetailsTopBar
import com.project.viewreview.presentation.details.components.MovieDetails
import com.project.viewreview.ui.theme.ViewReviewTheme
import com.project.viewreview.util.Constants.TMDB_BASE_URL

@Composable
fun DetailsScreen(
    movie: Movie,
    onEvent: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current

    Box(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        val scrollState = rememberLazyListState()
        MovieDetails(movie = movie, scrollState = scrollState)

        DetailsTopBar(
            onBookmarkClick = { onEvent(DetailsEvent.ToggleBookmark) },
            onFavouriteClick = { onEvent(DetailsEvent.ToggleFavourite) },
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
        DetailsScreen(movie = FightClub, onEvent = {}, navigateUp = {})
    }
}
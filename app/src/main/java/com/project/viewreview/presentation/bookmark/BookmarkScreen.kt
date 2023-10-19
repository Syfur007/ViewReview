package com.project.viewreview.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.project.viewreview.domain.model.Movie
import com.project.viewreview.presentation.common.MoviesList
import com.project.viewreview.ui.theme.MediumPadding

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Movie) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = MediumPadding, start = MediumPadding, end = MediumPadding)
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.padding(top = MediumPadding))

        MoviesList(
            movies = state.movies,
            onClick = navigateToDetails
        )

    }
}
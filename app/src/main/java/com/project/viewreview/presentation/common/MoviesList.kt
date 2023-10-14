package com.project.viewreview.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.project.viewreview.domain.model.MovieResponse
import com.project.viewreview.ui.theme.MediumPadding

@Composable
fun MoviesList(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<MovieResponse>,
    onClick: (MovieResponse) -> Unit
) {
    val handlePagingResult = handlePagingResult(movies = movies)
    if (handlePagingResult) {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Adaptive(140.dp),
            verticalArrangement = Arrangement.spacedBy(MediumPadding),
            horizontalArrangement = Arrangement.spacedBy(MediumPadding)
        ) {
            items(movies.itemCount) {
                movies[it]?.let {movie ->
                    MovieCard(movie = movie, onClick = { onClick(movie) })
                }
            }
        }
    }
}


@Composable
fun handlePagingResult(
    movies: LazyPagingItems<MovieResponse>,
): Boolean {
    val loadState = movies.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}


@Preview
@Composable
private fun ShimmerEffect() {
    LazyVerticalGrid(
        contentPadding = PaddingValues(horizontal = MediumPadding),
        columns = GridCells.Adaptive(140.dp),
        verticalArrangement = Arrangement.spacedBy(MediumPadding),
        horizontalArrangement = Arrangement.spacedBy(MediumPadding)
    ) {
        items(10) {
            MovieCardShimmerEffect()
        }
    }
}
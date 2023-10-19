package com.project.viewreview.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.project.viewreview.domain.model.MovieResponse
import com.project.viewreview.presentation.common.MoviesList
import com.project.viewreview.presentation.common.SearchBar
import com.project.viewreview.ui.theme.MediumPadding

@Composable
fun SearchScreen(
    state: SearchState,
    event:(SearchEvent) -> Unit,
    navigateToDetails: (MovieResponse) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(top = MediumPadding)
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPadding),
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchMovie) },
        )
        Spacer(modifier = Modifier.height(MediumPadding))
        state.movies?.let {
            val movies = it.collectAsLazyPagingItems()
            MoviesList(
                modifier = Modifier.padding(horizontal = MediumPadding),
                movies = movies,
                onClick = navigateToDetails
            )
        }
    }
}
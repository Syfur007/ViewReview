package com.project.viewreview.presentation.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.project.viewreview.R
import com.project.viewreview.domain.model.MovieResponse
import com.project.viewreview.presentation.common.MoviesList
import com.project.viewreview.ui.theme.MediumPadding
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    movies: LazyPagingItems<MovieResponse>,
    state: HomeState,
    event: (HomeEvent) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToDetails: (MovieResponse) -> Unit
) {
    val titles by remember {
        derivedStateOf {
            if (movies.itemCount > 0) {
                movies.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "ViewReview", fontSize = 25.sp, color = Color.White)

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .height(30.dp)
                    .clickable {
                        navigateToSearch()
                    },
                tint = Color.White,
            )

        }


        Spacer(modifier = Modifier.height(MediumPadding))

        val scrollState = rememberScrollState(initial = state.scrollValue)

        Text(
            text = titles, modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding)
                .horizontalScroll(scrollState, enabled = false),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        val tabTitles = listOf("Trending", "Top Rated", "Popular")
        var tabIndex by remember { mutableIntStateOf(0) }
        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f
        ) {
            tabTitles.size
        }

        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding),
            divider = { },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[tabIndex])
                        .padding(horizontal = 10.dp),
                )
            },
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }

        Spacer(modifier = Modifier.height(MediumPadding))


        // Update the maxScrollingValue
        LaunchedEffect(key1 = scrollState.maxValue) {
            event(HomeEvent.UpdateMaxScrollingValue(scrollState.maxValue))
        }
        // Save the state of the scrolling position
        LaunchedEffect(key1 = scrollState.value) {
            event(HomeEvent.UpdateScrollValue(scrollState.value))
        }
        // Animate the scrolling
        LaunchedEffect(key1 = state.maxScrollingValue) {
            delay(500)
            if (state.maxScrollingValue > 0) {
                scrollState.animateScrollTo(
                    value = state.maxScrollingValue,
                    animationSpec = infiniteRepeatable(
                        tween(
                            durationMillis = (state.maxScrollingValue - state.scrollValue) * 50_000 / state.maxScrollingValue,
                            easing = LinearEasing,
                            delayMillis = 1000
                        )
                    )
                )
            }
        }


        HorizontalPager(state = pagerState) { index ->
            when (index) {
                0 -> {
                    MoviesList(
                        modifier = Modifier.padding(horizontal = MediumPadding),
                        movies = movies,
                        onClick = navigateToDetails
                    )
                }
                1 -> {
                    MoviesList(
                        modifier = Modifier.padding(horizontal = MediumPadding),
                        movies = movies,
                        onClick = navigateToDetails
                    )
                }
                2 -> {
                    MoviesList(
                        modifier = Modifier.padding(horizontal = MediumPadding),
                        movies = movies,
                        onClick = navigateToDetails
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    val viewModel: HomeViewModel = hiltViewModel()
    val movies = viewModel.trendingMovies.collectAsLazyPagingItems()
    HomeScreen(movies = movies, navigateToDetails = {}, navigateToSearch = {}, state = HomeState(), event = {})
}


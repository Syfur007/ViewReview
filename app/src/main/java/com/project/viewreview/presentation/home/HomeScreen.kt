package com.project.viewreview.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.project.viewreview.R
import com.project.viewreview.domain.model.MovieResponse
import com.project.viewreview.presentation.common.MoviesList
import com.project.viewreview.presentation.common.SearchBar
import com.project.viewreview.presentation.navgraph.Route
import com.project.viewreview.ui.theme.MediumPadding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(movies: LazyPagingItems<MovieResponse>, navigate: (String) -> Unit) {
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
        Image(
            painter = painterResource(id = R.drawable.onebyzero_icon),
            contentDescription = null,
            modifier = Modifier
                .height(30.dp)
                .padding(horizontal = MediumPadding)
        )

        Spacer(modifier = Modifier.height(MediumPadding))

        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPadding),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = { navigate(Route.SearchScreen.route) },
            onSearch = {}
        )



        Spacer(modifier = Modifier.height(MediumPadding))

        Text(
            text = titles, modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding)
                .basicMarquee(), fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(MediumPadding))

        MoviesList(
            modifier = Modifier.padding(horizontal = MediumPadding),
            movies = movies,
            onClick = { navigate(Route.MovieDetailScreen.route) }
        )

    }
}

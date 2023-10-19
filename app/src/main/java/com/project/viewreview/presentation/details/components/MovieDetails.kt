package com.project.viewreview.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.insets.LocalWindowInsets
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.project.viewreview.R
import com.project.viewreview.domain.model.FightClub
import com.project.viewreview.domain.model.Movie
import com.project.viewreview.ui.theme.BackDropHeight
import com.project.viewreview.ui.theme.MediumPadding
import com.project.viewreview.ui.theme.VerySmallPadding
import com.project.viewreview.ui.theme.ViewReviewTheme
import com.project.viewreview.util.Constants.IMAGE_ORIGINAL_URL
import kotlin.math.max
import kotlin.math.min

@Composable
fun MovieDetails(movie: Movie, scrollState: LazyListState) {

    val maxOffset =
        with(LocalDensity.current) { BackDropHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top
    val currentOffset by remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    val offset = min(currentOffset, maxOffset)
    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset


    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {

            // BackDrop Image
            Column(
                modifier = Modifier.height(BackDropHeight)
            ) {

                Box(
                    Modifier
                        .height(BackDropHeight)
                        .graphicsLayer {
                            alpha = 1f - offsetProgress
                        }
                ) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(IMAGE_ORIGINAL_URL + movie.backdrop_path)
                            .error(R.drawable.onboarding_3)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colorStops = arrayOf(
                                        Pair(0.5f, Transparent),
                                        Pair(1f, MaterialTheme.colorScheme.background)
                                    )
                                )
                            )
                    )
                }
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(MediumPadding)
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.surface,
                            )
                        ) {
                            append(movie.title)
                        }

                        withStyle(
                            style = SpanStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        ) {
                            append(" (" + movie.release_date.subSequence(0, 4) + ")")
                        }
                    }
                )

                if (movie.tagline.isNotBlank()) {
                    Text(
                        text = "\"" + movie.tagline + "\"",
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(top = VerySmallPadding)
                    )
                }

                Spacer(modifier = Modifier.padding(VerySmallPadding))

                Row(verticalAlignment = Alignment.Bottom) {
                    RatingBar(
                        value = movie.vote_average.toFloat() / 2,
                        onValueChange = {},
                        onRatingChanged = {},
                        isIndicator = true,
                        size = 15.dp,
                        style = RatingBarStyle.Default,
                        numOfStars = 5,
                        stepSize = StepSize.ONE,
                    )
                    Text(
                        text = movie.vote_average.toString(),
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.padding(start = 10.dp),
                    )

                    Text(
                        text = "(${movie.vote_count} ratings)",
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.padding(start = 5.dp),
                    )
                }

                val genres by remember {
                    derivedStateOf {
                        if (movie.genres.isNotEmpty()) {
                            movie.genres.joinToString(separator = ", ") { it.name }
                        } else {
                            ""
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = VerySmallPadding)
                ) {

                    Text(text = genres, color = MaterialTheme.colorScheme.secondary)

                    Spacer(modifier = Modifier.padding(start = MediumPadding))

                    Icon(
                        painter = painterResource(id = R.drawable.ic_duration),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .padding(vertical = VerySmallPadding)
                            .height(16.dp)
                    )

                    Spacer(modifier = Modifier.padding(start = VerySmallPadding))

                    Text(text = "${movie.runtime} min", color = MaterialTheme.colorScheme.secondary)

                }

                Text(
                    text = "Release date: ${movie.release_date}",
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.padding(vertical = VerySmallPadding),
                    fontSize = 18.sp
                )


                Text(
                    text = movie.overview,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.padding(top = VerySmallPadding),
                )


            }
        }
    }


}


@Preview
@Composable
fun Test() {
    ViewReviewTheme {
        MovieDetails(movie = FightClub, scrollState = rememberLazyListState())
    }
}
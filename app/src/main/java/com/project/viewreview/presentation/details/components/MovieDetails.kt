package com.project.viewreview.presentation.details.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.google.firebase.auth.FirebaseAuth
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.project.viewreview.R
import com.project.viewreview.data.remote.dto.FightClub
import com.project.viewreview.data.remote.dto.FightClubCredits
import com.project.viewreview.data.remote.dto.Movie
import com.project.viewreview.data.remote.dto.MovieCredits
import com.project.viewreview.data.remote.dto.Review
import com.project.viewreview.presentation.common.MovieButton
import com.project.viewreview.ui.theme.BackDropHeight
import com.project.viewreview.ui.theme.MediumPadding
import com.project.viewreview.ui.theme.SmallPadding
import com.project.viewreview.ui.theme.VerySmallPadding
import com.project.viewreview.ui.theme.ViewReviewTheme
import com.project.viewreview.util.Constants.IMAGE_ORIGINAL_URL
import kotlin.math.max
import kotlin.math.min

@Composable
fun MovieDetails(
    movie: Movie,
    movieCredits: MovieCredits,
    movieReviews: List<Review>,
    onReviewPost: (String) -> Unit,
    onSignInClick: () -> Unit,
    scrollState: LazyListState,
) {

    val maxOffset =
        with(LocalDensity.current) { BackDropHeight.roundToPx() } - LocalWindowInsets.current.systemBars.layoutInsets.top
    val currentOffset by remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    val offset = min(currentOffset, maxOffset)
    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset


    var reviewText by remember { mutableStateOf("") }
    val context = LocalContext.current


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
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        ) {
                            append(movie.title)
                        }

                        withStyle(
                            style = SpanStyle(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        ) {
                            append(" (" + movie.release_date.subSequence(0, 4) + ")")
                        }
                    }
                )

                if (movie.tagline.isNotBlank()) {
                    Text(
                        text = "\"" + movie.tagline + "\"",
                        color = MaterialTheme.colorScheme.onBackground,
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
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(start = 10.dp),
                    )

                    Text(
                        text = "(${movie.vote_count} ratings)",
                        color = MaterialTheme.colorScheme.onBackground,
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

                    Text(text = genres, color = MaterialTheme.colorScheme.onBackground)

                    Spacer(modifier = Modifier.padding(start = MediumPadding))

                    Icon(
                        painter = painterResource(id = R.drawable.ic_duration),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier
                            .padding(vertical = VerySmallPadding)
                            .height(16.dp)
                    )

                    Spacer(modifier = Modifier.padding(start = VerySmallPadding))

                    Text(
                        text = "${movie.runtime} min",
                        color = MaterialTheme.colorScheme.onBackground
                    )

                }

                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        ) {
                            append("Release date: ")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onBackground,
                            )
                        ) {
                            append(movie.release_date)
                        }
                    },
                    modifier = Modifier.padding(vertical = VerySmallPadding)
                )


                Text(
                    text = movie.overview,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = VerySmallPadding),
                )

                Spacer(modifier = Modifier.padding(top = MediumPadding))

                Text(
                    text = "Cast",
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = VerySmallPadding),
                )

                LazyRow {
                    items(5) {
                        val cast = movieCredits.cast[it]
                        CastCard(cast = cast)
                    }
                }

                Spacer(modifier = Modifier.padding(top = MediumPadding))

                Text(
                    text = "Reviews",
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(top = VerySmallPadding),
                )

                Spacer(modifier = Modifier.padding(top = MediumPadding))


                if (FirebaseAuth.getInstance().currentUser != null) {
                    ReviewField(reviewText = reviewText,
                        onReviewTextChange = { reviewText = it },
                        onReviewPost = {
                            onReviewPost(reviewText)
                            Toast.makeText(
                                context,
                                "Review Posted!",
                                Toast.LENGTH_SHORT
                            ).show()
                            reviewText = ""
                        }
                    )
                } else {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(MediumPadding),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Login To Post Reviews")

                        Spacer(modifier = Modifier.padding(SmallPadding))

                        MovieButton(
                            text = "Sign In",
                            onClick = { onSignInClick() }
                        )
                    }
                }
            }
        }

        items(movieReviews) { review ->
            ReviewCard(review = review)
        }


    }


}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Test() {
    ViewReviewTheme {
        MovieDetails(
            movie = FightClub,
            movieCredits = FightClubCredits,
            movieReviews = emptyList(),
            onReviewPost = {},
            onSignInClick = {},
            scrollState = rememberLazyListState()
        )
    }
}
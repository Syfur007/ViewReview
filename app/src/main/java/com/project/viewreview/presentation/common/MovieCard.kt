package com.project.viewreview.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.project.viewreview.domain.model.SniperGRIT
import com.project.viewreview.domain.model.MovieResponse
import com.project.viewreview.ui.theme.VerySmallPadding
import com.project.viewreview.ui.theme.ViewReviewTheme
import com.project.viewreview.util.Constants.IMAGE_500_URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: MovieResponse,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Column(modifier = modifier
        .clickable { onClick() }
        .width(150.dp)
    ) {

        AsyncImage(
            model = ImageRequest.Builder(context).data(IMAGE_500_URL + movie.poster_path).build(),
            contentDescription = movie.title,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop

        )
        Spacer(modifier = Modifier.height(VerySmallPadding))
        Text(text = movie.title)
    }

}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MovieCardPreview() {
    ViewReviewTheme {
        MovieCard(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            movie = SniperGRIT,
            onClick = {}
        )
    }
}
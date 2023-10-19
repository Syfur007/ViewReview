package com.project.viewreview.presentation.details.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.viewreview.R
import com.project.viewreview.ui.theme.Black
import com.project.viewreview.ui.theme.ViewReviewTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBookmarkClick: () -> Unit,
    onFavouriteClick: () -> Unit,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    isBookmarked: Boolean = false,
    isFavourite: Boolean = false,
) {

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body),
        ),
        title = {},
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    Modifier
                        .background(color = Black.copy(alpha = 0.25f), shape = CircleShape)
                        .padding(7.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        },
        actions = {

            IconButton(
                onClick = onBookmarkClick
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isBookmarked) R.drawable.ic_bookmarked else R.drawable.ic_bookmark
                    ),
                    contentDescription = null,
                    Modifier
                        .background(color = Black.copy(alpha = 0.25f), shape = CircleShape)
                        .padding(5.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            IconButton(
                onClick = onFavouriteClick
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isFavourite) R.drawable.ic_favorited else R.drawable.ic_favorite
                    ),
                    contentDescription = null,
                    Modifier
                        .background(color = Black.copy(alpha = 0.25f), shape = CircleShape)
                        .padding(5.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            IconButton(
                onClick = onShareClick
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    Modifier
                        .background(color = Black.copy(alpha = 0.25f), shape = CircleShape)
                        .padding(5.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
        },
    )
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailsTopBar() {
    ViewReviewTheme {
        DetailsTopBar(
            onFavouriteClick = { /*TODO*/ },
            onShareClick = { /*TODO*/ },
            onBookmarkClick = { /*TODO*/ },
            onBackClick = { /*TODO*/ },
            isBookmarked = true,
        )
    }
}
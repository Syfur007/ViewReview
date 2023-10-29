package com.project.viewreview.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.project.viewreview.R
import com.project.viewreview.presentation.onboarding.Page
import com.project.viewreview.ui.theme.SemiLargePadding
import com.project.viewreview.ui.theme.SmallPadding
import com.project.viewreview.ui.theme.ViewReviewTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier) {
        Spacer(modifier = Modifier.fillMaxHeight(0.45f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        ) {
            Text(
                text = page.title,
                Modifier
                    .padding(horizontal = SemiLargePadding),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(SmallPadding))
            Text(
                text = page.description,
                Modifier
                    .padding(horizontal = SemiLargePadding),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingPagePreview() {
    ViewReviewTheme {
        OnBoardingPage(
            page = Page(
                title = "Welcome to ViewReview",
                description = "See. Share. Discover.",
                image = R.drawable.onboarding_2
            )
        )
    }
}
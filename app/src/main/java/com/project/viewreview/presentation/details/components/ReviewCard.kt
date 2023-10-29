package com.project.viewreview.presentation.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.project.viewreview.data.remote.dto.Review
import com.project.viewreview.ui.theme.MediumPadding
import com.project.viewreview.ui.theme.VerySmallPadding

@Composable
fun ReviewCard(review: Review) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MediumPadding, vertical = VerySmallPadding),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(MediumPadding)
        ) {
            Text(text = review.reviewText, fontSize = 16.sp)
            Spacer(modifier = Modifier.padding(VerySmallPadding))
            Text(
                text = "- ${review.authorId}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                fontStyle = FontStyle.Italic
            )
        }
    }

}

@Preview
@Composable
fun ReviewCardPreview() {
    ReviewCard(
        Review(
            reviewText = "This is a review",
            authorId = "123",
        )
    )
}
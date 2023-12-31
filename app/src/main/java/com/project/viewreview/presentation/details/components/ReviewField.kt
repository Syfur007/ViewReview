package com.project.viewreview.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.viewreview.presentation.common.MovieButton
import com.project.viewreview.ui.theme.VerySmallPadding
import com.project.viewreview.ui.theme.ViewReviewTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewField(
    reviewText: String,
    onReviewTextChange: (String) -> Unit,
    onReviewPost: () -> Unit
) {


    Column (horizontalAlignment = Alignment.End) {
        Text(
            text = "Post A review",
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.padding(5.dp))

        OutlinedTextField(
            value = reviewText,
            onValueChange = { onReviewTextChange(it) },
            placeholder = { Text(text = "Write something...", fontSize = 14.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            shape = MaterialTheme.shapes.small,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
            ),
        )
        Spacer(modifier = Modifier.padding(VerySmallPadding))

        Row (horizontalArrangement = Arrangement.SpaceBetween) {

        }

        MovieButton(
            text = "Post Review",
            onClick = { onReviewPost() }
        )
    }


}


@Preview
@Composable
fun ReviewFieldPreview() {
    ViewReviewTheme {
        ReviewField(reviewText = "", onReviewTextChange = {}) {

        }
    }
}
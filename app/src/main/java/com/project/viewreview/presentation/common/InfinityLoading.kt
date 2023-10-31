package com.project.viewreview.presentation.common

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.project.viewreview.R

@Composable
fun InfinityLoading() {
    val loadingComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.infinity_loop))
    val progress by animateLottieCompositionAsState(
        composition = loadingComposition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = loadingComposition,
        progress = { progress },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight()
    )
}
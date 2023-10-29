package com.project.viewreview.data.remote.dto


import androidx.annotation.Keep
import com.google.firebase.Timestamp

@Keep
data class Review (
    val reviewText: String = "",
    val authorId: String = "",
    val reviewTime: Timestamp = Timestamp.now(),
)


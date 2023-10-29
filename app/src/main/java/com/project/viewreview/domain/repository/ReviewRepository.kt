package com.project.viewreview.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.project.viewreview.data.remote.dto.Review

interface ReviewRepository {

    fun postReview(movieId: Int, review: Review)

    fun getReviews(movieId: Int): Task<QuerySnapshot>
}
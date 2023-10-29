package com.project.viewreview.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.project.viewreview.data.remote.dto.Review
import com.project.viewreview.domain.repository.ReviewRepository

class ReviewRepositoryImpl : ReviewRepository {

    private val db = FirebaseFirestore.getInstance()

    override fun postReview(movieId: Int, review: Review) {
        db.collection("reviews")
            .document(movieId.toString())
            .collection("movieReviews")
            .add(review)
    }

    override fun getReviews(movieId: Int): Task<QuerySnapshot> {
        return db.collection("reviews")
            .document(movieId.toString())
            .collection("movieReviews")
            .get()
    }
}
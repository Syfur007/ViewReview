package com.project.viewreview.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.project.viewreview.data.remote.dto.Movie
import com.project.viewreview.data.remote.dto.MovieCredits
import com.project.viewreview.data.remote.dto.Review
import com.project.viewreview.domain.repository.ReviewRepository
import com.project.viewreview.domain.usecases.movie.DeleteMovie
import com.project.viewreview.domain.usecases.movie.GetMovie
import com.project.viewreview.domain.usecases.movie.GetMovieCredits
import com.project.viewreview.domain.usecases.movie.GetSavedMovie
import com.project.viewreview.domain.usecases.movie.UpsertMovie
import com.project.viewreview.util.UIComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieUseCase: GetMovie,
    private val getMovieCreditsUseCase: GetMovieCredits,
    private val getSavedMovieUseCase: GetSavedMovie,
    private val deleteMovieUseCase: DeleteMovie,
    private val upsertMovieUseCase: UpsertMovie,
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    var selectedMovie = mutableStateOf<Movie?>(null)
        private set

    suspend fun selectMovie(movieId: Int) {
        selectedMovie.value = getMovieUseCase(movieId)
    }


    var movieCredits = mutableStateOf<MovieCredits?>(null)
        private set

    suspend fun getMovieCredits(movieId: Int) {
        movieCredits.value = getMovieCreditsUseCase(movieId)
    }


    private fun postReview(movieId: Int, review: Review) {
        reviewRepository.postReview(movieId, review)
    }

    var reviews = mutableStateOf<List<Review>>(emptyList())
        private set

    fun fetchReviews(movieId: Int) {
        reviewRepository.getReviews(movieId).addOnSuccessListener { snapshots ->
            val reviewsList = snapshots.toObjects(Review::class.java)
            reviews.value = reviewsList
        }
    }


    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteMovie -> {
                viewModelScope.launch {
                    val movie = getSavedMovieUseCase(id = event.movie.id)
                    if (movie == null) {
                        upsertMovie(movie = event.movie)
                    } else {
                        deleteMovie(movie = event.movie)
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }

            is DetailsEvent.PostReview -> {
                viewModelScope.launch {
                    postReview(
                        event.movieId,
                        review = Review(
                            reviewText = event.review,
                            authorId = FirebaseAuth.getInstance().currentUser!!.uid
                        )
                    )
                }
            }
        }
    }

    private suspend fun deleteMovie(movie: Movie) {
        deleteMovieUseCase(movie = movie)
        sideEffect = UIComponent.Toast("Bookmark Removed")
    }

    private suspend fun upsertMovie(movie: Movie) {
        upsertMovieUseCase(movie = movie)
        sideEffect = UIComponent.Toast("Movie Bookmarked")
    }
}
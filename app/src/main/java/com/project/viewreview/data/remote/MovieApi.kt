package com.project.viewreview.data.remote

import com.project.viewreview.data.remote.dto.MovieList
import com.project.viewreview.domain.model.Movie
import com.project.viewreview.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): MovieList

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): MovieList

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("query") query: String
    ): MovieList

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Query("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Movie
}
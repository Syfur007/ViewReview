package com.project.viewreview.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.project.viewreview.domain.model.MovieResponse
import com.project.viewreview.util.Constants

class SearchPagingSource(
    private val movieApi: MovieApi,
    private val searchQuery: String
):PagingSource<Int, MovieResponse>() {
    private var totalMovieCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse> {
        val page = params.key ?: 1
        return try {
            val moviesResponse = movieApi.searchMovies(page = page, apiKey = Constants.API_KEY, query = searchQuery)
            totalMovieCount += moviesResponse.results.size
            val movies = moviesResponse.results.distinctBy { it.id }
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (totalMovieCount >= moviesResponse.total_results) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
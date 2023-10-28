package com.project.viewreview.domain.model

data class MovieBasic(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>?,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

val SniperGRIT = MovieBasic(
    adult = false,
    backdrop_path = "/1DBo2V4KyBWXuagt4JOR2jZJMHB.jpg",
    genre_ids = listOf(28, 53),
    id = 1171541,
    original_language = "en",
    original_title = "Sniper: G.R.I.T. - Global Response & Intelligence Team",
    overview = "When an international terrorist cult threatens global political stability and kidnaps a fellow agent, Ace Sniper Brandon Beckett and the newly-formed Global Response & Intelligence Team – or G.R.I.T. – led by Colonel Stone must travel across the world to Malta, infiltrate the cult, and take out its leader to free Lady Death and stop the global threat.",
    popularity = 621.191,
    poster_path = "/gcd5TJwXLWeQ2Dn0aRxI8OJIIxK.jpg",
    release_date = "2023-09-26",
    title = "Sniper: G.R.I.T. - Global Response & Intelligence Team",
    video = false,
    vote_average = 7.4,
    vote_count = 25
)
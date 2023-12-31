package com.project.viewreview.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.viewreview.domain.model.BelongsToCollection
import com.project.viewreview.domain.model.Genre
import com.project.viewreview.domain.model.ProductionCompany
import com.project.viewreview.domain.model.ProductionCountry
import com.project.viewreview.domain.model.SpokenLanguage

@Entity
data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    @PrimaryKey val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

val FightClub = Movie(
        adult = false,
        backdrop_path = "/hZkgoQYus5vegHoetLkCJzb17zJ.jpg",
        belongs_to_collection = null,
        budget = 63000000,
        genres = listOf(
            Genre(
                id = 18,
                name = "Drama"
            ),
        ),
        homepage = "http://www.foxmovies.com/movies/fight-club",
        id = 550,
        imdb_id = "tt0137523",
        original_language = "en",
        original_title = "Fight Club",
        overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
        popularity = 70.617,
        poster_path = "/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg",
        production_companies = listOf(
            ProductionCompany(
                id = 508,
                logo_path = "/7cxRWzi4LsVm4Utfpr1hfARNurT.png",
                name = "Regency Enterprises",
                origin_country = "US"
            ),
            ProductionCompany(
                id = 711,
                logo_path = "/tEiIH5QesdheJmDAqQwvtN60727.png",
                name = "Fox 2000 Pictures",
                origin_country = "US"
            ),
            ProductionCompany(
                id = 20555,
                logo_path = "/hD8yEGUBlHOcfHYbujp71vD8gZp.png",
                name = "Taurus Film",
                origin_country = "DE"
            ),
            ProductionCompany(
                id = 54051,
                logo_path = null,
                name = "Atman Entertainment",
                origin_country = ""
            ),
            ProductionCompany(
                id = 54052,
                logo_path = null,
                name = "Knickerbocker Films",
                origin_country = "US"
            ),
            ProductionCompany(
                id = 4700,
                logo_path = "/A32wmjrs9Psf4zw0uaixF0GXfxq.png",
                name = "The Linson Company",
                origin_country = "US"
            ),
            ProductionCompany(
                id = 25,
                logo_path = "/qZCc1lty5FzX30aOCVRBLzaVmcp.png",
                name = "20th Century Fox",
                origin_country = "US"
            )
        ),
        production_countries = listOf(
            ProductionCountry(
                iso_3166_1 = "US",
                name = "United States of America"
            ),
        ),
        release_date = "1999-10-15",
        revenue = 100853753,
        runtime = 139,
        spoken_languages = listOf(
            SpokenLanguage(
                english_name = "English",
                iso_639_1 = "en",
                name = "English"
            ),
        ),
        status = "Released",
        tagline = "Mischief. Mayhem. Soap.",
        title = "Fight Club",
        video = false,
        vote_average = 8.438,
        vote_count = 27322
)
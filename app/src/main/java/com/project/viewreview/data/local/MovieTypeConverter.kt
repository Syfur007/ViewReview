package com.project.viewreview.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.project.viewreview.domain.model.BelongsToCollection
import com.project.viewreview.domain.model.Genre
import com.project.viewreview.domain.model.ProductionCompany
import com.project.viewreview.domain.model.ProductionCountry
import com.project.viewreview.domain.model.SpokenLanguage

@ProvidedTypeConverter
class MovieTypeConverter {

    @TypeConverter
    fun belongsToCollectionToString(belongsToCollection: BelongsToCollection?): String {
        return "${belongsToCollection?.id}-${belongsToCollection?.name}-${belongsToCollection?.poster_path}-${belongsToCollection?.backdrop_path}"
    }

    @TypeConverter
    fun stringToBelongsToCollection(belongsToCollection: String): BelongsToCollection? {
        if (belongsToCollection == "null-null-null-null") return null
        val (id, name, poster_path, backdrop_path) = belongsToCollection.split("-")
        return BelongsToCollection(id.toInt(), name, poster_path, backdrop_path)
    }

    @TypeConverter
    fun genreListToString(genres: List<Genre>): String {
        return genres.joinToString(separator = ",") { "${it.id}:${it.name}" }
    }

    @TypeConverter
    fun stringToGenreList(genres: String): List<Genre> {
        return genres.split(",").map {
            val (id, name) = it.split(":")
            Genre(id.toInt(), name)
        }
    }

    @TypeConverter
    fun productionCompaniesListToString(productionCompanies: List<ProductionCompany>): String {
        return productionCompanies.joinToString(separator = ":") { "${it.id}-${it.logo_path}-${it.name}-${it.origin_country}" }
    }

    @TypeConverter
    fun stringToProductionCompaniesList(productionCompanies: String): List<ProductionCompany> {
        return productionCompanies.split(":").map {
            val (id, logo_path, name, origin_country) = it.split("-")
            ProductionCompany(id.toInt(), logo_path, name, origin_country)
        }
    }

    @TypeConverter
    fun productionCountriesListToString(productionCountries: List<ProductionCountry>): String {
        return productionCountries.joinToString(separator = ",") { "${it.iso_3166_1}:${it.name}" }
    }

    @TypeConverter
    fun stringToProductionCountriesList(productionCountries: String): List<ProductionCountry> {
        return productionCountries.split(",").map {
            val (iso_3166_1, name) = it.split(":")
            ProductionCountry(iso_3166_1, name)
        }
    }

    @TypeConverter
    fun spokenLanguagesListToString(spokenLanguages: List<SpokenLanguage>): String {
        return spokenLanguages.joinToString(separator = ",") { "${it.iso_639_1}:${it.name}:${it.english_name}" }
    }

    @TypeConverter
    fun stringToSpokenLanguagesList(spokenLanguages: String): List<SpokenLanguage> {
        return spokenLanguages.split(",").map {
            val (iso_639_1, name, english_name) = it.split(":")
            SpokenLanguage(iso_639_1, name, english_name)
        }
    }

}
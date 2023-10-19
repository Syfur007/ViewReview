package com.project.viewreview.di

import android.app.Application
import androidx.room.Room
import com.project.viewreview.data.local.MovieDao
import com.project.viewreview.data.local.MovieDatabase
import com.project.viewreview.data.local.MovieTypeConverter
import com.project.viewreview.data.remote.MovieApi
import com.project.viewreview.util.Constants.API_BASE_URL
import com.project.viewreview.util.Constants.MOVIE_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApiInstance(): MovieApi {
        return Retrofit
            .Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(
        application: Application
    ): MovieDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = MovieDatabase::class.java,
            name = MOVIE_DATABASE_NAME
        ).addTypeConverter(MovieTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        movieDatabase: MovieDatabase
    ): MovieDao = movieDatabase.movieDao

}
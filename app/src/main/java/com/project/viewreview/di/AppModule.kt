package com.project.viewreview.di

import android.app.Application
import com.project.viewreview.data.manager.LocalUserManagerImpl
import com.project.viewreview.data.remote.MovieApi
import com.project.viewreview.data.repository.MovieRepositoryImpl
import com.project.viewreview.domain.manager.LocalUserManager
import com.project.viewreview.domain.repository.MovieRepository
import com.project.viewreview.domain.usecases.app_entry.AppEntryUseCases
import com.project.viewreview.domain.usecases.app_entry.ReadAppEntry
import com.project.viewreview.domain.usecases.app_entry.SaveAppEntry
import com.project.viewreview.domain.usecases.movie.GetPopularMovies
import com.project.viewreview.domain.usecases.movie.GetTrendingMovies
import com.project.viewreview.domain.usecases.movie.MovieUseCases
import com.project.viewreview.util.Constants.API_BASE_URL
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
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManager
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

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
    fun provideMovieRepository(
        movieApi: MovieApi
    ): MovieRepository = MovieRepositoryImpl(movieApi)

    @Provides
    @Singleton
    fun provideMovieUseCases(
        movieRepository: MovieRepository
    ): MovieUseCases {
        return MovieUseCases(
            getPopularMovies = GetPopularMovies(movieRepository),
            getTrendingMovies = GetTrendingMovies(movieRepository)
        )
    }

}
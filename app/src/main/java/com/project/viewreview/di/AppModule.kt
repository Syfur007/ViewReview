package com.project.viewreview.di

import android.app.Application
import com.project.viewreview.data.manager.LocalUserManagerImpl
import com.project.viewreview.domain.manager.LocalUserManager
import com.project.viewreview.domain.usecases.AppEntryUseCases
import com.project.viewreview.domain.usecases.ReadAppEntry
import com.project.viewreview.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}
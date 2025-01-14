package com.example.masterand.di.repository

import com.example.masterand.data.repository.PlayerSessionRepository
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
    fun providePlayerSessionRepository(): PlayerSessionRepository {
        return PlayerSessionRepository()
    }
}
package com.example.masterand.di.repository

import com.example.masterand.data.repository.ScoresRepository
import com.example.masterand.data.repository.ScoresRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ScoresModule {
    @Binds
    abstract fun bindScoresRepository(scoresRepositoryImpl: ScoresRepositoryImpl): ScoresRepository
}
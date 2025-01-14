package com.example.masterand.di.repository

import com.example.masterand.data.repository.PlayersRepository
import com.example.masterand.data.repository.PlayersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PlayerModule {
    @Binds
    abstract fun bindPlayersRepository(playersRepositoryImpl: PlayersRepositoryImpl): PlayersRepository
}
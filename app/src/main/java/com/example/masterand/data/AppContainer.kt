package com.example.masterand.data


import android.content.Context
import com.example.masterand.data.database.HighScoreDatabase
import com.example.masterand.data.repository.PlayerScoresRepository
import com.example.masterand.data.repository.PlayerScoresRepositoryImpl
import com.example.masterand.data.repository.PlayersRepository
import com.example.masterand.data.repository.PlayersRepositoryImpl
import com.example.masterand.data.repository.ScoresRepository
import com.example.masterand.data.repository.ScoresRepositoryImpl

interface AppContainer {
    val playersRepository: PlayersRepository
    val scoresRepository: ScoresRepository
    val playerScoresRepository: PlayerScoresRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val playersRepository: PlayersRepository by lazy {
        PlayersRepositoryImpl(HighScoreDatabase.getDatabase(context).playerDao())
    }

    override val scoresRepository: ScoresRepository by lazy {
        ScoresRepositoryImpl(HighScoreDatabase.getDatabase(context).scoreDao())
    }

    override val playerScoresRepository: PlayerScoresRepository by lazy {
        PlayerScoresRepositoryImpl(HighScoreDatabase.getDatabase(context).playerScoreDao())
    }
}
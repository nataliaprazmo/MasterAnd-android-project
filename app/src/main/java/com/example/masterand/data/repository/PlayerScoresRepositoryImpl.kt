package com.example.masterand.data.repository

import com.example.masterand.data.dao.PlayerScoreDao
import com.example.masterand.data.model.PlayerWithScore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayerScoresRepositoryImpl @Inject constructor(private val playerScoreDao: PlayerScoreDao) :
    PlayerScoresRepository {
    override fun loadPlayersWithScores(): Flow<List<PlayerWithScore>> {
        return playerScoreDao.loadPlayersWithScores()
    }
}
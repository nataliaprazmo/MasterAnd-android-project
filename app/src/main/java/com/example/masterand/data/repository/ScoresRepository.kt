package com.example.masterand.data.repository

import com.example.masterand.data.model.PlayerWithScore
import com.example.masterand.data.model.Score
import kotlinx.coroutines.flow.Flow

interface ScoresRepository {
    suspend fun insertScore(score: Score): Long
    fun getScoreStream(scoreId: Long): Flow<Score>
    fun getPlayerScores(playerId: Long): Flow<List<Score>>
    fun getTopScores(): Flow<List<Score>>
}
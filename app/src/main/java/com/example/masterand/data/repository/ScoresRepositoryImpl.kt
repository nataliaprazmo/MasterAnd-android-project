package com.example.masterand.data.repository

import com.example.masterand.data.dao.ScoreDao
import com.example.masterand.data.model.Score
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScoresRepositoryImpl @Inject constructor(private val scoreDao: ScoreDao) : ScoresRepository {
    override suspend fun insertScore(score: Score): Long = scoreDao.insert(score)

    override fun getScoreStream(scoreId: Long): Flow<Score> = scoreDao.getScoreStream(scoreId)

    override fun getPlayerScores(playerId: Long): Flow<List<Score>> =
        scoreDao.getPlayerScores(playerId)

    override fun getTopScores(): Flow<List<Score>> = scoreDao.getTopScores()
}
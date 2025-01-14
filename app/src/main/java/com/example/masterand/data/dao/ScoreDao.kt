package com.example.masterand.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.masterand.data.model.Score
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(score: Score): Long

    @Query("SELECT * FROM scores WHERE scoreId = :scoreId")
    fun getScoreStream(scoreId: Long): Flow<Score>

    @Query("SELECT * FROM scores WHERE playerId = :playerId ORDER BY gameScore ASC")
    fun getPlayerScores(playerId: Long): Flow<List<Score>>

    @Query("SELECT * FROM scores ORDER BY gameScore ASC LIMIT 5")
    fun getTopScores(): Flow<List<Score>>
}
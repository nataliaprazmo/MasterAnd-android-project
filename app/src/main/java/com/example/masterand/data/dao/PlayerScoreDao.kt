package com.example.masterand.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.masterand.data.model.PlayerWithScore
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerScoreDao {
    @Query(
        """
        SELECT players.playerId AS playerId, 
               players.name AS playerName, 
               players.email AS playerEmail, 
               scores.scoreId AS scoreId, 
               scores.gameScore AS gameScore 
        FROM players 
        INNER JOIN scores ON players.playerId = scores.playerId
        ORDER BY gameScore ASC
        """
    )
    fun loadPlayersWithScores(): Flow<List<PlayerWithScore>>
}

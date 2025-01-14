package com.example.masterand.data.repository

import androidx.lifecycle.LiveData
import com.example.masterand.data.model.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PlayersRepository {
    suspend fun insertPlayer(player: Player): Long
    suspend fun updatePlayer(player: Player)
    fun getAllPlayersStream(): Flow<List<Player>>
    fun getPlayerStream(playerId: Int): Flow<Player?>
    fun getPlayerByEmail(email: String): Flow<Player?>
}
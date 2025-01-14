package com.example.masterand.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.masterand.data.dao.PlayerDao
import com.example.masterand.data.model.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class PlayersRepositoryImpl @Inject constructor(private val playerDao: PlayerDao) : PlayersRepository {
    override suspend fun insertPlayer(player: Player): Long = playerDao.insert(player)

    override suspend fun updatePlayer(player: Player) =
        playerDao.update(player)

    override fun getAllPlayersStream(): Flow<List<Player>> =
        playerDao.getAllPlayersStream()

    override fun getPlayerStream(playerId: Int): Flow<Player?> =
        playerDao.getPlayerStream(playerId)

    override fun getPlayerByEmail(email: String): Flow<Player?> =
        playerDao.getPlayerByEmail(email)
}
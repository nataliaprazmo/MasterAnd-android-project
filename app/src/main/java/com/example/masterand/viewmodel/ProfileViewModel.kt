package com.example.masterand.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.masterand.data.model.Player
import com.example.masterand.data.repository.PlayerSessionRepository
import com.example.masterand.data.repository.PlayersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val playersRepository: PlayersRepository,
    private val playerSessionRepository: PlayerSessionRepository
) : ViewModel() {
    val playerId = mutableLongStateOf(0L)
    val name = mutableStateOf("")
    val email = mutableStateOf("")

    suspend fun savePlayer() {
        val existingPlayer = playersRepository.getPlayerByEmail(email.value).firstOrNull()
        if (existingPlayer != null) {
            if (existingPlayer.name != name.value) {
                Log.d(
                    "ProfileViewModel",
                    "Updated player name from ${existingPlayer.name} to ${name.value}"
                )
                existingPlayer.name = name.value
                playersRepository.updatePlayer(existingPlayer)
            }
            playerSessionRepository.setCurrentPlayerId(existingPlayer.playerId)
        } else {
            val player = Player(
                name = name.value,
                email = email.value
            )
            val id = playersRepository.insertPlayer(player)
            playerSessionRepository.setCurrentPlayerId(id)
            Log.d(
                "ProfileViewModel",
                "New player inserted with name ${player.name} and mail ${player.email}"
            )
        }
    }
}
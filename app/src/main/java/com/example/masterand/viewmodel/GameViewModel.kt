package com.example.masterand.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masterand.data.model.Score
import com.example.masterand.data.repository.PlayerSessionRepository
import com.example.masterand.data.repository.PlayersRepository
import com.example.masterand.data.repository.ScoresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val scoresRepository: ScoresRepository,
    playerSessionRepository: PlayerSessionRepository
) :
    ViewModel() {
    val score = mutableIntStateOf(0)
    val playerId = mutableLongStateOf(0L)

    init {
        viewModelScope.launch {
            playerSessionRepository.currentPlayerId.collect { id ->
                playerId.longValue = id
            }
        }
    }

    fun saveScore() {
        viewModelScope.launch {
            scoresRepository.insertScore(
                Score(
                    playerId = playerId.longValue,
                    gameScore = score.intValue
                )
            )
            Log.d("GameViewModel", "Saved score: ${score.intValue}, playerId: ${playerId.longValue}")
        }
    }
}
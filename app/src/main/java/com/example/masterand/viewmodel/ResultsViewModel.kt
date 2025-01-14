package com.example.masterand.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masterand.data.repository.PlayerScoresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val playerScoresRepository: PlayerScoresRepository
) : ViewModel() {
    private val _topScores = MutableStateFlow<List<PlayerScoreUI>>(emptyList())
    val topScores: StateFlow<List<PlayerScoreUI>> get() = _topScores

    init {
        loadTopScores()
    }

    private fun loadTopScores() {
        viewModelScope.launch {
            playerScoresRepository.loadPlayersWithScores()
                .map { playerWithScores ->
                    playerWithScores.map { playerWithScore ->
                        PlayerScoreUI(
                            playerName = playerWithScore.playerName,
                            score = playerWithScore.gameScore
                        )
                    }
                }
                .collect { playerScores ->
                    _topScores.value = playerScores
                }
            Log.d("ResultsViewModel", "Loaded top scores")
        }
    }
}

data class PlayerScoreUI(
    val playerName: String,
    val score: Int
)
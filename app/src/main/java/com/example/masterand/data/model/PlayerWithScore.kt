package com.example.masterand.data.model

data class PlayerWithScore(
    val playerId: Long,
    val playerName: String,
    val playerEmail: String,
    val scoreId: Long,
    val gameScore: Int
)

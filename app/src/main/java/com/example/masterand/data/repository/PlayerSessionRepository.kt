package com.example.masterand.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerSessionRepository @Inject constructor() {
    private val _currentPlayerId = MutableStateFlow<Long>(0L)
    val currentPlayerId: StateFlow<Long> = _currentPlayerId.asStateFlow()

    fun setCurrentPlayerId(id: Long) {
        _currentPlayerId.value = id
    }
}
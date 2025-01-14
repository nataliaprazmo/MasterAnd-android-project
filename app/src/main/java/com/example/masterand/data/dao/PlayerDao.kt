package com.example.masterand.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.masterand.data.model.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(player: Player): Long

    @Update
    suspend fun update(player: Player)

    @Query("SELECT * from players")
    fun getAllPlayersStream(): Flow<List<Player>>

    @Query("SELECT * from players WHERE playerId = :playerId")
    fun getPlayerStream(playerId: Int): Flow<Player>

    @Query("SELECT * FROM players WHERE email = :email")
    fun getPlayerByEmail(email: String): Flow<Player?>
}
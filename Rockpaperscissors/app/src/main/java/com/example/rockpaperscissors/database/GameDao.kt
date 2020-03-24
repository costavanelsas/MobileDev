package com.example.rockpaperscissors.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.rockpaperscissors.model.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM rps_game ORDER BY date DESC")
    suspend fun getAllGames(): List<Game>

    @Query("SELECT COUNT(result) FROM rps_game WHERE result = 'You win!'")
    suspend fun getAllWinGames(): Int

    @Query("SELECT COUNT(result) FROM rps_game WHERE result = 'Draw'")
    suspend fun getAllDrawGames(): Int

    @Query("SELECT COUNT(result) FROM rps_game WHERE result = 'Computer wins!'")
    suspend fun getAllLostGames(): Int

    @Insert
    suspend fun insertGame(game: Game)

    @Query("DELETE FROM rps_game")
    suspend fun deleteAllGames()
}
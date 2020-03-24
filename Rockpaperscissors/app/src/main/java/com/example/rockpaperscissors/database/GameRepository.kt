package com.example.rockpaperscissors.database

import android.content.Context
import com.example.rockpaperscissors.model.Game

class GameRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database =
            GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    /**
     * gets all the games
     */
    suspend fun getAllGames(): List<Game> {
        return gameDao.getAllGames()
    }

    /**
     * gets all the games that have been won
     */
    suspend fun getAllWinGames(): Int {
        return gameDao.getAllWinGames()
    }

    /**
     * gets all the games that are a draw
     */
    suspend fun getAllDrawGames(): Int {
        return gameDao.getAllDrawGames()
    }

    /**
     * gets all the games that are lost
     */
    suspend fun getAllLostGames(): Int {
        return gameDao.getAllLostGames()
    }

    /**
     * starts a new game
     */
    suspend fun insertGame(product: Game) {
        gameDao.insertGame(product)
    }

    /**
     * Delete all games (refresh history)
     */
    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }
}
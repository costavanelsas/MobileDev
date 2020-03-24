package com.example.rockpaperscissors.ui

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameRepository
import com.example.rockpaperscissors.model.Game
import com.example.rockpaperscissors.model.GameEnum

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

const val VIEW_HISTORY_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {
    private lateinit var gameRepository: GameRepository

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Rock, Paper, Scissors game"

        val rock = findViewById<ImageView>(R.id.ivRock)
        val paper = findViewById<ImageView>(R.id.ivPaper)
        val scissors = findViewById<ImageView>(R.id.ivScissors)

        rock.setOnClickListener {
            newGame(0)
        }
        paper.setOnClickListener {
            newGame(1)
        }
        scissors.setOnClickListener {
            newGame(2)
        }

        gameRepository = GameRepository(this)

        getStatistics()
    }

    @SuppressLint("SetTextI18n")
    @TargetApi(Build.VERSION_CODES.O)
    private fun newGame(playerMove : Int) {
        val computerMove = (0..2).shuffled().first()
        val result = calculateWin(playerMove, computerMove)

        CoroutineScope(Dispatchers.Main).launch {
            val game = Game(
                date = LocalDateTime.now(),
                computerMove = getEnumType(computerMove),
                playerMove = getEnumType(playerMove),
                result = result
            )
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }

            tvResult.text = game.result
            ivComputer.setImageDrawable(getDrawable(game.computerMove.imageUrl))
            ivPlayer.setImageDrawable(getDrawable(game.playerMove.imageUrl))
        }
        getStatistics()
    }

    private fun getEnumType(move : Int) : GameEnum {
        if(move == 0) return GameEnum.ROCK else if (move == 1) {
            return GameEnum.PAPER
        }
        return GameEnum.SCISSORS
    }

    @SuppressLint("SetTextI18n")
    private fun getStatistics() {
        CoroutineScope(Dispatchers.Main).launch {
            tvStatistics.text = getString(R.string.all_time_statistics)
            tvWin.text = getString(R.string.win) + gameRepository.getAllWinGames()
            tvDraw.text = getString(R.string.draw) + gameRepository.getAllDrawGames()
            tvLost.text = getString(R.string.lost) + gameRepository.getAllLostGames()
        }
    }

    private fun calculateWin(playerMove : Int, computerMove : Int) : String {
        if(playerMove == computerMove) {
            return "Draw"
        } else if((playerMove == GameEnum.ROCK.value && computerMove == GameEnum.SCISSORS.value) ||
            (playerMove == GameEnum.PAPER.value && computerMove == GameEnum.ROCK.value) ||
            (playerMove == GameEnum.SCISSORS.value && computerMove == GameEnum.PAPER.value)) {
            return "You win!"
        }
        return "Computer wins!"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val deleteItem = menu.findItem(R.id.action_delete_icon)
        deleteItem.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_history_icon -> {
                viewHistory()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun viewHistory() {
        val intent = Intent(this, GameHistory::class.java)
        startActivityForResult(intent, VIEW_HISTORY_REQUEST_CODE)
    }

    override fun onResume() {
        super.onResume()
        getStatistics()
    }
}
package com.example.gamebacklog.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


const val ADD_GAME_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private val games = arrayListOf<Game>()
    private val gameAdapter = GameAdapter(games)
    private val savedGames = arrayListOf<Game>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Game backlog"

        initViews()
        initViewModel()
    }

    private fun startAddActivity() {
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, ADD_GAME_REQUEST_CODE)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_GAME_REQUEST_CODE -> {
                    val game = data!!.getParcelableExtra<Game>(EXTRA_GAME)
                    mainActivityViewModel.insertGame(game)
                }
            }
        }
    }

    private fun initViews() {
        rvGames.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter
        rvGames.addItemDecoration(DividerItemDecoration(this@MainActivity, 0))
        createItemTouchHelper().attachToRecyclerView(rvGames)

        fab.setOnClickListener { startAddActivity() }
    }

    private fun initViewModel() {
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.games.observe(this, Observer { games ->
            this@MainActivity.games.clear()
            this@MainActivity.games.addAll(games)
            gameAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val game = games[position]

                if(direction == ItemTouchHelper.LEFT) {
                    mainActivityViewModel.deleteGame(game)
                    Snackbar
                        .make(viewHolder.itemView, "Successfully deleted game", Snackbar.LENGTH_LONG)
                        .setAction("UNDO"){
                            mainActivityViewModel.insertGame(game)
                        }
                        .show()
                }

                gameAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_icon -> {
                savedGames.clear()
                savedGames.addAll(games)
                mainActivityViewModel.deleteAllGames()
                Snackbar.make(rvGames, "Successfully deleted game", Snackbar.LENGTH_LONG)
                    .setAction("UNDO") {
                        savedGames.forEach {
                            mainActivityViewModel.insertGame(it)
                        }
                    }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}

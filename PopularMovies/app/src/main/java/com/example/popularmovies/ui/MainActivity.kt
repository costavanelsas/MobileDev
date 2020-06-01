package com.example.popularmovies.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie

import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.floor

/**
 * Created by Costa van Elsas on 28-5-2020.
 */
const val EXTRA_MOVIE = "EXTRA_MOVIE"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val movies = arrayListOf<Movie>()
    private val movieAdapter = MovieAdapter(movies) {movieItem -> onMovieClick(movieItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        val gridLayoutManager = GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)
        rvMovies.layoutManager = gridLayoutManager

        rvMovies.adapter = movieAdapter

        rvMovies.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                rvMovies.viewTreeObserver.removeOnGlobalLayoutListener(this)
                gridLayoutManager.spanCount = calculateSpanCount()
                gridLayoutManager.requestLayout()
            }
        })

        btnSubmit.setOnClickListener {
            viewModel.getMovies(etYear.text.toString())
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.moviesList.observe(this, Observer {
            movies.clear()
            movies.addAll(it.results)
            movieAdapter.notifyDataSetChanged()
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun calculateSpanCount(): Int {
        val viewWidth = rvMovies.measuredWidth
        val cardViewWidth = resources.getDimension(R.dimen.poster_width)
        val cardViewMargin = resources.getDimension(R.dimen.margin_medium)
        val spanCount = floor((viewWidth / (cardViewWidth + cardViewMargin)).toDouble()).toInt()
        return if (spanCount >= 1) spanCount else 1
    }

    private fun onMovieClick(movieItem: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(EXTRA_MOVIE, movieItem)
        startActivity(intent)
    }
}
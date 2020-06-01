package com.example.popularmovies.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.popularmovies.R
import com.example.popularmovies.model.Movie
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_details.*


/**
 * Created by Costa van Elsas on 28-5-2020.
 */
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViews()
    }

    private fun initViews() {
        movie = intent.extras?.getParcelable(EXTRA_MOVIE)!!

        tvTitle.text = movie.title
        Glide.with(this).load(movie.getPosterImage()).into(ivPoster)
        Glide.with(this).load(movie.getBackdropImage()).into(ivBackdrop)
        tvRating.text = movie.vote_average.toString()
        tvReleaseDate.text = "Release: ${movie.release_date}"
        tvOverview.text = movie.overview
    }
}

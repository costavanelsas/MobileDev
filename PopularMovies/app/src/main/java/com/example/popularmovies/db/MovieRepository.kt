package com.example.popularmovies.db

import android.content.Context
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.MoviesList
import retrofit2.Call

/**
 * Created by Costa van Elsas on 28-5-2020.
 */
class MovieRepository(var context: Context) {
    private val movieApi: MovieApiService = MovieApi.createApi()

    fun getMovies(year: String): Call<MoviesList> {
        return movieApi.getMovies(year)
    }
}
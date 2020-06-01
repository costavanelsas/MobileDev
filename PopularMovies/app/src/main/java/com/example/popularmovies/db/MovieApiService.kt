package com.example.popularmovies.db

import com.example.popularmovies.BuildConfig
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Costa van Elsas on 1-6-2020.
 */
interface MovieApiService {
    @GET("https://api.themoviedb.org/3/discover/movie?api_key=${BuildConfig.API_KEY}&language=en-US&sort_by=popularity.desc&include_video=false")
    fun getMovies(@Query("year") year: String): Call<MoviesList>
}
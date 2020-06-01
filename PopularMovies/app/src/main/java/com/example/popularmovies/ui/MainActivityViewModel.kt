package com.example.popularmovies.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.popularmovies.db.MovieRepository
import com.example.popularmovies.model.Movie
import com.example.popularmovies.model.MoviesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Costa van Elsas on 28-5-2020.
 */
class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepository = MovieRepository(application.applicationContext)
    val error = MutableLiveData<String>()
    val moviesList = MutableLiveData<MoviesList>()

    fun getMovies(year: String) {
        moviesRepository.getMovies(year).enqueue(object : Callback<MoviesList> {
            override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                if (response.isSuccessful) moviesList.value = response.body()
                else error.value = "An error has occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}
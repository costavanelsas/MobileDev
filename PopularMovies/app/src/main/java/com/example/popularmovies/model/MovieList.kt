package com.example.popularmovies.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Costa van Elsas on 28-5-2020.
 */
data class MoviesList (
    @SerializedName("results") val results : List<Movie>
)
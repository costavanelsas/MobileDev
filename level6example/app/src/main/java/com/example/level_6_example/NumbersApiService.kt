package com.example.level_6_example

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Costa van Elsas on 28-5-2020.
 */

interface NumbersApiService {

    // The GET method needed to retrieve a random number trivia.
    @GET("/random/trivia?json")
    fun getRandomNumberTrivia(): Call<Trivia>
}

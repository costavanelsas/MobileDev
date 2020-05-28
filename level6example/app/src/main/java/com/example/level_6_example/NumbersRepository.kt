package com.example.level_6_example

/**
 * Created by Costa van Elsas on 28-5-2020.
 */
class NumbersRepository {
    private val numbersApi: NumbersApiService = NumbersApi.createApi()

    fun getRandomNumberTrivia() = numbersApi.getRandomNumberTrivia()
}

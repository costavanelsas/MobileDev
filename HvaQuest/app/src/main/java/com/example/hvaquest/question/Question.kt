package com.example.hvaquest.question

/**
 * Created by Costa van Elsas on 2-6-2020.
 */
data class Question(
    var question: String,
    var choices: Array<String>,
    var correctAnswer: String,
    var clue: Int
)

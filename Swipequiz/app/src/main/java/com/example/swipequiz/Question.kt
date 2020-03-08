package com.example.swipequiz

data class Question(
    var name: String,
    var answer: Boolean
) {
    companion object {
        val QUESTIONS = arrayOf(
            "The capital of the Netherlands is Amsterdam",
            "2 + 2 = 4 - 1 = 3",
            "There are 3200 seconds in 1 hour",
            "The president of the USA is Obama",
            "This course is enjoyable according to Costa"
        )
        val ANSWERS = arrayOf(
            true,
            true,
            false,
            false,
            true

        )
    }
}
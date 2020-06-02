package com.example.level_7_example

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

/**
 * Created by Costa van Elsas on 2-6-2020.
 */

class QuizViewModel : ViewModel() {

    var question: String = ""
    var answer: String = ""

    fun isAnswerCorrect(answer: String): Boolean {
        return answer.toLowerCase() == this.answer.toLowerCase()
    }

    fun createQuestion(question: String, answer: String) {
        this.question = question
        this.answer = answer
    }

    fun isQuestionCreated() : Boolean {
        return question.isNotBlank() && answer.isNotBlank()
    }

}
package com.example.hvaquest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hvaquest.question.QuestRepository
import com.example.hvaquest.question.Question

/**
 * Created by Costa van Elsas on 2-6-2020.
 */
class QuestViewModel : ViewModel() {
    private val questRepository = QuestRepository()

    var questIndexTracker = 0

    fun getQuestion(i: Int) : Question{
        return questRepository.getQuestion(i)
    }

    fun getListSize() : Int {
        return questRepository.getListSize()
    }
}
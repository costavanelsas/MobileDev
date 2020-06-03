package com.example.hvaquest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquest.R
import com.example.hvaquest.question.Question
import com.example.hvaquest.viewmodel.QuestViewModel
import kotlinx.android.synthetic.main.fragment_question.*

/**
 * Created by Costa van Elsas on 2-6-2020.
 */
class QuestionFragment : Fragment() {

    private lateinit var viewModel : QuestViewModel
    private lateinit var question: Question
    private lateinit var myView: View
    private val args: QuestionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Question"
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_question, container, false)
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnConfirm.setOnClickListener {
            if (rgQuestions.checkedRadioButtonId != -1) {

                val choiceText = myView.findViewById<RadioButton>(rgQuestions.checkedRadioButtonId)?.text

                if ( choiceText == question.correctAnswer) {
                    val action = QuestionFragmentDirections.actionQuestionFragmentToLocationFragment(args.progressIndex, args.pageIndex)
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(context, "Wrong answer", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Please select an answer", Toast.LENGTH_LONG).show()
            }
        }

        viewModel = ViewModelProvider(this).get(QuestViewModel::class.java)

        initViews()
    }

    private fun initViews(){
        question = viewModel.getQuestion(args.progressIndex)
        tvProgress.text = getString(R.string.progress_text, args.pageIndex, viewModel.getListSize())

        tvQuestion.text = question.question

        answer1.text = question.choices[0]
        answer2.text = question.choices[1]
        answer3.text = question.choices[2]

        viewModel.questIndexTracker = args.progressIndex
    }
}
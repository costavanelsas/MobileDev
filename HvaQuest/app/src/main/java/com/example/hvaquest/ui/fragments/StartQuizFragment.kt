package com.example.hvaquest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hvaquest.R
import kotlinx.android.synthetic.main.fragment_start_quiz.*

/**
 * Created by Costa van Elsas on 2-6-2020.
 */
class StartQuizFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnStart.setOnClickListener{
            val action = StartQuizFragmentDirections.actionStartFragmentToQuestionFragment()
            findNavController().navigate(action)
        }
    }

}
package com.example.hvaquest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquest.R
import com.example.hvaquest.question.Question
import com.example.hvaquest.viewmodel.QuestViewModel
import kotlinx.android.synthetic.main.fragment_location.*

/**
 * Created by Costa van Elsas on 2-6-2020.
 */
class LocationFragment : Fragment() {

    private lateinit var viewModel : QuestViewModel
    private lateinit var question: Question
    private lateinit var locationImage: ImageView
    private lateinit var myView: View

    private val args: LocationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Location Clue"
        myView = inflater.inflate(R.layout.fragment_location, container, false)
        locationImage = myView.rootView.findViewById(R.id.ivLocation)

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNextQuestion.setOnClickListener {
            if (args.progressIndex + 1 < viewModel.getListSize()) {
                val action = LocationFragmentDirections.actionLocationFragmentToQuestionFragment(args.progressIndex + 1, args.pageIndex + 1)
                findNavController().navigate(action)
            } else {
                val action = LocationFragmentDirections.actionLocationFragmentToEndFragment()
                findNavController().navigate(action)
            }
        }

        viewModel = ViewModelProvider(this).get(QuestViewModel::class.java)
        initViews()
    }

    private fun initViews(){
        question = viewModel.getQuestion(args.progressIndex)
        var image = question.clue

        locationImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), image))
    }
}
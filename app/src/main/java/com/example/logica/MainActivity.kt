package com.example.logica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Variable for the amount of correct answers
    private var correctAnswers: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

    }

    /**
     * when the submit button is clicked, check the answer and updateUI with the texts
     */
    private fun initViews() {
        btnSubmit.setOnClickListener { checkAnswer(); updateUI() }
    }

    /**
     * Generate text for the incorrect/correct answers
     */
    private fun updateUI() {

        if (correctAnswers == 4) {
            return Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
        }
        return Toast.makeText(this, getString(R.string.incorrect, correctAnswers), Toast.LENGTH_LONG).show()
        correctAnswers = 0
    }

    /**
     * Check if the answer is correct, first get the answers then put them in a array
     * At last check if they are correct or not and show the toast messages
     */
    private fun checkAnswer() {
        val answer1 = txtInput1.text.toString()
        val answer2 = txtInput2.text.toString()
        val answer3 = txtInput3.text.toString()
        val answer4 = txtInput4.text.toString()

        val answers = arrayOf(answer1, answer2, answer3, answer4)

        if (getString(R.string.t) in answers[0]) {
            correctAnswers++
        }

        if (getString(R.string.f) in answers[1]) {
            correctAnswers++
        }

        if (getString(R.string.f) in answers[2]) {
            correctAnswers++
        }

        if (getString(R.string.f) in answers[3]) {
            correctAnswers++
        }

//        if (answer1 == getString(R.string.t)
//            && answer2 == getString(R.string.f)
//            && answer3 == getString(R.string.f)
//            && answer4 == getString(R.string.f)
//        ) {
//            return Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_LONG).show()
//        }
//
//        return Toast.makeText(this, getString(R.string.incorrectToast), Toast.LENGTH_LONG).show()
    }
}

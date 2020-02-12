package com.example.higherlowergame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class HigherLowerActivity : AppCompatActivity() {

    private var currentThrow: Int = 1
    private var lastThrow: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btnHigher.setOnClickListener { onHigherClick() }
        btnLower.setOnClickListener { onLowerClick() }
        btnEquals.setOnClickListener { onEqualClick() }
        updateUI()
    }

    /**
     * Set the images when the case is 1..6 and update the last throw text
     */
    private fun updateUI() {
        txtViewLastThrow.text = getString(R.string.last_throw, lastThrow)

        when (currentThrow) {
            1 -> imgView.setImageResource(R.drawable.dice1)
            2 -> imgView.setImageResource(R.drawable.dice2)
            3 -> imgView.setImageResource(R.drawable.dice3)
            4 -> imgView.setImageResource(R.drawable.dice4)
            5 -> imgView.setImageResource(R.drawable.dice5)
            6 -> imgView.setImageResource(R.drawable.dice6)
        }
    }

    /**
     * roll the dice 1..6 random number
     */
    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    /**
     * On lower button click method
     */
    private fun onLowerClick() {
        rollDice()

        if (currentThrow < lastThrow) {
            onAnswerCorrect()
        } else onAnswerIncorrect()
    }

    /**
     * On equal button click method
     */
    private fun onEqualClick() {
        rollDice()

        if (currentThrow == lastThrow) {
            onAnswerCorrect()
        } else onAnswerIncorrect()
    }

    /**
     * On higher button click method
     */
    private fun onHigherClick() {
        rollDice()

        if (currentThrow > lastThrow) {
            onAnswerCorrect()
        } else onAnswerIncorrect()
    }

    /**
     * when the answer is correct display correct toast
     */
    private fun onAnswerCorrect() {
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
    }

    /**
     * when the answer is incorrect display incorrect toast
     */
    private fun onAnswerIncorrect() {
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
    }
}

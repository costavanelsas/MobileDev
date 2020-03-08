package com.example.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
        questionAdapter.notifyDataSetChanged()

        initViews()
    }


    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                questionAdapter.notifyItemChanged(position)

                val swipedLeft = 4
                val swipedRight = 8
                val answerType = questions[position].answer

                if((direction == swipedLeft && answerType) || (direction == swipedRight && !answerType)) {
                    return getCorrectSnackBar(viewHolder, answerType)
                }

                return getInCorrectSnackBar(viewHolder, answerType)
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun getCorrectSnackBar(viewHolder: RecyclerView.ViewHolder, answer: Boolean) {
        Snackbar.make(viewHolder.itemView, getString(R.string.correct_answer)
                + " " + answer, Snackbar.LENGTH_LONG).show()
    }

    private fun getInCorrectSnackBar(viewHolder: RecyclerView.ViewHolder, answer: Boolean) {
        Snackbar.make(viewHolder.itemView, getString(R.string.incorrect_answer)
                + " " + answer, Snackbar.LENGTH_LONG).show()
    }

    private fun initViews() {
        rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }
}

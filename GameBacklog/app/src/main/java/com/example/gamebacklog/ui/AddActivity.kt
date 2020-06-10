package com.example.gamebacklog.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.TypeConverters
import com.example.gamebacklog.R.layout.activity_add
import com.example.gamebacklog.database.Converters
import com.example.gamebacklog.model.Game
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*
import java.lang.Exception
import java.text.SimpleDateFormat

/**
 * Created by Costa van Elsas on 22-5-2020.
 */
const val EXTRA_GAME = "EXTRA_GAME"

@TypeConverters(Converters::class)
class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_add)
        setSupportActionBar(toolbar)

        initViews()
    }

    //init view
    private fun initViews() {
        fab.setOnClickListener { onSaveClick() }
    }

    /**
     * method to validate the fields and check if they are not empty and a correct date
     */
    private fun validateEmptyFields(): Boolean {
        val zero = 0
        val maxDays = 31
        val maxMonths = 12
        val minYear = 1900
        val maxYear = 9999

        //title check
        if (etTitle.text.toString().isBlank()) {
            Toast.makeText(this,"Please fill in a title"
                , Toast.LENGTH_SHORT).show()
            return false
        }

        //platform check
        if (etPlatform.text.toString().isBlank()) {
            Toast.makeText(this,"Please fill in a platform"
                , Toast.LENGTH_SHORT).show()
            return false
        }

        //date check
        if (etDay.text.toString().isBlank() || etMonth.text.toString().isBlank() ||
            etYear.text.toString().isBlank()) {
            Toast.makeText(this,"Please fill in a date"
                , Toast.LENGTH_SHORT).show()
            return false
        }

        //day check
        if(etDay.text.toString().toInt() < zero || etDay.text.toString().toInt() > maxDays){
            etDay.error = "Fill in a valid day"
            return false
        }

        //month check
        if(etMonth.text.toString().toInt() < zero || etMonth.text.toString().toInt() > maxMonths){
            etMonth.error = "Fill in a valid month"
            return false
        }

        //year check
        if(etYear.text.toString().toInt() < minYear || etYear.text.toString().toInt() > maxYear){
            etYear.error = "Fill in a valid year"
            return false
        }

        return true
    }

    /**
     * method for the onsave button click
     */
    @SuppressLint("SimpleDateFormat")
    private fun onSaveClick() {
        if (validateEmptyFields()) {
            val concatenatedString = (etDay.text.toString() + "-" + etMonth.text.toString() + "-" + etYear.text.toString())
            try {
                val date = SimpleDateFormat("dd-MM-yyyy")
                date.isLenient = false
                val parsedDate = date.parse(concatenatedString)

                val game = Game(etTitle.text.toString(), etPlatform.text.toString(), parsedDate)
                val resultIntent = Intent()

                resultIntent.putExtra(EXTRA_GAME, game)
                setResult(Activity.RESULT_OK, resultIntent)

                finish()
            } catch (e : Exception) {
                Toast.makeText(this,"Please fill in a valid date"
                    , Toast.LENGTH_SHORT).show()
            }
        }
    }

}
package com.example.hvaquest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.hvaquest.R
import com.example.hvaquest.viewmodel.QuestViewModel

/**
 * Created by Costa van Elsas on 2-6-2020.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: QuestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProvider(this).get(QuestViewModel::class.java)
        initNavigation()
    }

    private fun initNavigation(){

        val navController = findNavController(R.id.navHostFragment)

        val appBarConfiguration = AppBarConfiguration(navController.graph)

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        when (item.itemId) {
            android.R.id.home -> {
                // Check whether there is a progress in the quest.
                if (viewModel.questIndexTracker > 0) {
                    viewModel.questIndexTracker -= 1
                }
                // Navigate up in the hierarchy of fragments.
                navController.navigateUp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
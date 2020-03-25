package com.example.studentportal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val ADD_REMINDER_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private val websites = arrayListOf<Website>()
    private val websiteAdapter = WebsiteAdapter(websites){websiteLink: Website -> webisteClicked(websiteLink)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        fab.setOnClickListener { startAddActivity() }
    }

    private fun initViews(){
        rvWebsites.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvWebsites.adapter = websiteAdapter
        createItemTouchHelper().attachToRecyclerView(rvWebsites)
    }

    private fun webisteClicked(websiteLink: Website) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
        customTabsIntent.launchUrl(this, Uri.parse(websiteLink.url))
    }

    /**
     * method for swiping to the left to delete
     */
    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                websites.removeAt(position)
                websiteAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun startAddActivity() {
        val intent = Intent(this, ActivityAdd::class.java)
        startActivityForResult(intent, ADD_REMINDER_REQUEST_CODE)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_REMINDER_REQUEST_CODE -> {
                    val website = data!!.getParcelableExtra<Website>(EXTRA_WEBSITE)
                    websites.add(website)
                    websiteAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}

package com.example.studentportal

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_website.view.*

class WebsiteAdapter(private val reminders: List<Website> , private val clickListener: (Website) -> Unit): RecyclerView.Adapter<WebsiteAdapter.ViewHolder>() {

    /**
     * Creates and returns a ViewHolder object
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_website, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return reminders.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reminders[position], clickListener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(website: Website, clickListener: (Website) -> Unit) {
            itemView.websiteButton.text = website.name + "\n" + website.url
            itemView.websiteButton.setOnClickListener {
                clickListener(website)
            }
        }
    }
}
package com.example.rockpaperscissors.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.Game
import kotlinx.android.synthetic.main.game_item.view.*

class GameAdapter(private val products: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.game_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.tvDate.text = (game.date).toString()
            itemView.ivComputer.setImageDrawable(context.getDrawable(game.computerMove.imageUrl))
            itemView.ivPlayer.setImageDrawable(context.getDrawable(game.playerMove.imageUrl))
            itemView.tvResultHistory.text = game.result
        }
    }
}
package com.example.madlevel5task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_game.view.*
import java.text.SimpleDateFormat
import java.util.*

class GameAdapter (private val games: List<Game>):
    RecyclerView.Adapter<GameAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun databind(game: Game) {
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)

            itemView.textViewName.text = game.title
            itemView.textViewPlatform.text = game.platform
            itemView.textViewReleaseDate.text = itemView.context.getString(R.string.releaseDate, dateFormat.format(game.releaseDate))
        }
    }
}
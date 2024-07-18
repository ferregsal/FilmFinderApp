package com.example.filmfinderapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmfinderapp.data.Film
import com.example.filmfinderapp.databinding.ItemFilmBinding
import com.squareup.picasso.Picasso

class FilmAdapter (private var dataSet: List<Film>, val onItemClickListener: (Int)->Unit):
    RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context))
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.render(dataSet[position])
        holder.itemView.setOnClickListener{onItemClickListener(position)}

    }

    override fun getItemCount(): Int = dataSet.size
    fun updateData(dataSet: List<Film>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }


    class FilmViewHolder(private val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {

        fun render(film: Film) {
            binding.titleTextView.text = film.title
            binding.yearTextView.text = film.year
            Picasso.get().load(film.poster).into(binding.posterImageView)
        }
    }
}